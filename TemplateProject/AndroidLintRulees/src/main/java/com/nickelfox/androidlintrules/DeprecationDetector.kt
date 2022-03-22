package com.nickelfox.androidlintrules

import com.android.tools.lint.detector.api.*
import com.intellij.psi.PsiClassType
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.*

@Suppress("UnstableApiUsage")
class DeprecationDetector : Detector(), SourceCodeScanner {
    override fun applicableAnnotations(): List<String> = listOf(
        "java.lang.Deprecated"
    )

    override fun visitAnnotationUsage(
        context: JavaContext,
        usage: UElement,
        type: AnnotationUsageType,
        annotation: UAnnotation,
        qualifiedName: String,
        method: PsiMethod?,
        referenced: PsiElement?,
        annotations: List<UAnnotation>,
        allMemberAnnotations: List<UAnnotation>,
        allClassAnnotations: List<UAnnotation>,
        allPackageAnnotations: List<UAnnotation>
    ) {
        method?.let {
            if (it.isDeprecated || it.containingClass?.isDeprecated == true)
                reportUsage(context, usage, annotation)
        }
    }

    private fun isValidExperimentalUsage(
        context: JavaContext,
        usage: UElement,
        annotation: UAnnotation
    ): Boolean {

        // Feature annotation (ex. @TimeTravel) that is @Experimental
        val featureName = (annotation.uastParent as? UClass)
            ?.qualifiedName ?: return false

        // Find the nearest enclosing annotated element
        var element: UAnnotated? = if (usage is UAnnotated) {
            usage
        } else {
            usage.getParentOfType(UAnnotated::class.java)
        }

        while (element != null) {
            val annotations = context.evaluator.getAllAnnotations(element, false)

            // Is the element itself part of the same experimental
            // feature set, e.g. annotated with @TimeTravelExperiment?
            if (annotations.any { it.qualifiedName == featureName })
                return true

            // Or does it explicitly opt-in as a user, e.g.
            // annotated with @UseExperimental(TimeTravelExperiment.class)?
            if (annotations
                    .filter { annot -> annot.qualifiedName == "kotlin.UseExperimental" }
                    .mapNotNull { annot -> annot.attributeValues.getOrNull(0) }
                    .any { attr -> attr.getFullyQualifiedName(context) == featureName }
            )
                return true

            // Traverse to the next enclosing annotated element
            element = element.getParentOfType(UAnnotated::class.java)
        }
        return false
    }


    /**
     * Reports an issue.
     */
    private fun reportUsage(
        context: JavaContext,
        usage: UElement,
        annotation: UAnnotation
    ) {
        //val useAnnotation = (annotation.uastParent as? UClass)?.qualifiedName ?: return

        context.report(
            ISSUE, usage, context.getNameLocation(usage), """
            Ye deprecated method hai, ise mat use kar chutiye
        """.trimIndent()
        )


    }

    companion object {
        private val IMPLEMENTATION = Implementation(
            DeprecationDetector::class.java,
            Scope.JAVA_FILE_SCOPE
        )

        @Suppress("DefaultLocale")
        val ISSUE: Issue = Issue.create(
            id = "UnsafeDeprecationUsageError",
            briefDescription = "Unsafe deprecation usage",
            explanation = """
                This API has been flagged as deprecation.
                Any declaration annotated with this marker is considered part of an unstable API \
                surface and its call sites should accept the deprecation aspect of it either by \
                using `@UseDeprecation`, or by being annotated with that marker themselves, \
                effectively causing further propagation of that deprecation aspect.
            """,
            category = Category.CORRECTNESS,
            priority = 9,
            severity = Severity.ERROR,
            implementation = IMPLEMENTATION
        )
    }
}

/**
 * Returns the fully-qualified class name for a given attribute value, if any.
 */
private fun UNamedExpression?.getFullyQualifiedName(context: JavaContext): String? {
    val exp = this?.expression
    val type = if (exp is UClassLiteralExpression) exp.type else exp?.evaluate()
    return (type as? PsiClassType)?.let { context.evaluator.getQualifiedName(it) }
}
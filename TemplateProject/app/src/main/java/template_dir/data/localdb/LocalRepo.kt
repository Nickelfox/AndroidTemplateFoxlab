package template_package.data.db

import javax.inject.Inject


//TODO: remove this if not needed
@Suppress("unused")
class LocalRepo @Inject constructor(private val database: GenieDatabase)
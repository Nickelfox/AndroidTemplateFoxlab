package template_package.data.models

import androidx.room.ColumnInfo

data class CategoriesModel(
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "category_display_name") val name: String,
    @ColumnInfo(name = "isactive") val isActive: String?,
)
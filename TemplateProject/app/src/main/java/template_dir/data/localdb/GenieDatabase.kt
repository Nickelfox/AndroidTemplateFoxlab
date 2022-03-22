package template_package.data.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import template_package.data.localdb.dao.CategoryDao
import template_package.data.localdb.entities.Categories
import template_package.data.localdb.entities.CategoryImages

@Database(entities = [Categories::class, CategoryImages::class], version = 1, exportSchema = false)
abstract class GenieDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}
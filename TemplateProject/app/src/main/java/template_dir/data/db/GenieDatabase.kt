package template_package.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import template_package.data.db.dao.CategoryDao
import template_package.data.db.dao.EmployeesDao
import template_package.data.db.entities.Categories
import template_package.data.db.entities.CategoryImages
import template_package.data.db.entities.Employees

@Database(entities = [Categories::class, CategoryImages::class, Employees::class], version = 1, exportSchema = false)
abstract class GenieDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun employeesDao(): EmployeesDao
}
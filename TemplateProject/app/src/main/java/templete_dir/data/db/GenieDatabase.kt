package templete_dir.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import templete_dir.data.db.dao.CategoryDao
import templete_dir.data.db.dao.EmployeesDao
import templete_dir.data.db.entities.Categories
import templete_dir.data.db.entities.CategoryImages
import templete_dir.data.db.entities.Employees

@Database(entities = [Categories::class, CategoryImages::class, Employees::class], version = 1, exportSchema = false)
abstract class GenieDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun employeesDao(): EmployeesDao
}
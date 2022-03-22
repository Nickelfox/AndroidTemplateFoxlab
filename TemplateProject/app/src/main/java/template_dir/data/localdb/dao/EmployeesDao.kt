package template_package.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import template_package.data.db.entities.Employees

@Dao
interface EmployeesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdateEmployees(list: List<Employees>)

    @Query("Select * from Employees")
    fun getAllEmployees(): LiveData<List<Employees>>

}
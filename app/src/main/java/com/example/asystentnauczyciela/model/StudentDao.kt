package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    suspend fun insertStudent(student:Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student:Student)

    @Query("SELECT * FROM STUDENT_TABLE")
    fun allStudent(): LiveData<List<Student>>
}
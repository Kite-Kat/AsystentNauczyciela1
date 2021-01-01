package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentCourseDao {

    @Insert
    suspend fun insertSC(studentCourse: StudentCourse)

    @Update
    suspend fun updateSC(studentCourse: StudentCourse)

    @Delete
    suspend fun deleteSC(studentCourse: StudentCourse)

    @Query("Select * from STUDENT_COURSE_TABLE")
    fun allSC(): LiveData<List<StudentCourse>>

}
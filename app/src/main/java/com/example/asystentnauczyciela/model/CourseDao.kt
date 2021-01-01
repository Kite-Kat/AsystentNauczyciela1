package com.example.asystentnauczyciela.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CourseDao {

    @Insert
    suspend fun insertCourse(course:Course)

    @Update
    suspend fun updateCourse(course: Course)

    @Delete
    suspend fun deleteCourse(course:Course)

    @Query("Select * from COURSE_TABLE")
    fun allCourses(): LiveData<List<Course>>
}
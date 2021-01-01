package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.CourseDao

class CourseRepository(private val courseDao: CourseDao) {


    val readAll:LiveData<List<Course>> = courseDao.allCourses()

    suspend fun add(course:Course)=courseDao.insertCourse(course)

    suspend fun delete(course:Course)=courseDao.deleteCourse(course)

    suspend fun update(course: Course)=courseDao.updateCourse(course)

}
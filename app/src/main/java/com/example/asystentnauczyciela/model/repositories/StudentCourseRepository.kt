package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.model.StudentCourseDao

class StudentCourseRepository(private val studentCourseDao:StudentCourseDao) {
    val readAll: LiveData<List<StudentCourse>> = studentCourseDao.allSC()

    suspend fun add(studentCourse: StudentCourse)=studentCourseDao.insertSC(studentCourse)


    suspend fun delete(studentCourse: StudentCourse)=studentCourseDao.deleteSC(studentCourse)

    suspend fun update(studentCourse: StudentCourse)=studentCourseDao.updateSC(studentCourse)

    suspend fun getStudentCourses(courseId: Int) = studentCourseDao.get(courseId)

}
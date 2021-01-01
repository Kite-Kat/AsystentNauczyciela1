package com.example.asystentnauczyciela.model.repositories

import androidx.lifecycle.LiveData
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentDao

class StudentRepository(private val studentDao: StudentDao) {
    val readAll:LiveData<List<Student>> = studentDao.allStudent()

    suspend fun add(student:Student)=studentDao.insertStudent(student)


    suspend fun delete(student:Student)=studentDao.deleteStudent(student)

    suspend fun update(student: Student)=studentDao.updateStudent(student)


}
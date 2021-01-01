package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.repositories.StudentRepository

class StudentViewModel(application: Application): AndroidViewModel(application) {

    val students: LiveData<List<Student>>
    private val studentRepository:StudentRepository

    init{
        students=SCDatabase.getDatabase(application).studentDao().allStudent()
        studentRepository= StudentRepository(SCDatabase.getDatabase(application).studentDao())
    }

    fun addStudent(name:String, surname:String)
    {
        //musi byc wykonane asynchronicznie
        viewModelScope.launch {
            studentRepository.add(Student(id = 0,name = name, surname = surname))
        }
    }

    fun deleteStudent(student: Student)
    {
        viewModelScope.launch {
            studentRepository.delete(student)
        }

    }

    fun updateStudent(student: Student){
        viewModelScope.launch {
            studentRepository.update(student)
        }
    }

}
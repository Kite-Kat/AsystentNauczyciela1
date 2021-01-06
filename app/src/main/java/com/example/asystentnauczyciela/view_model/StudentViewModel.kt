package com.example.asystentnauczyciela.view_model

import android.app.Application
import android.os.Build
import android.text.TextUtils.lastIndexOf
import android.util.Log
import android.widget.ListAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.model.repositories.StudentCourseRepository
import com.example.asystentnauczyciela.model.repositories.StudentRepository



class StudentViewModel(application: Application): AndroidViewModel(application) {

    val students: LiveData<List<Student>> = SCDatabase.getDatabase(application).studentDao().allStudent()
    val studentCourses: LiveData<List<StudentCourse>> = SCDatabase.getDatabase(application).studentCourseDao().allSC()

    private val studentRepository:StudentRepository = StudentRepository(SCDatabase.getDatabase(application).studentDao())

    val navigation = MutableLiveData<Int?>()




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

    fun editStudent(student: Student){
        navigation.postValue(student.id)

    }

    fun onNavigationCompleted(){
        navigation.value = null
    }



//    @RequiresApi(Build.VERSION_CODES.N)
//    fun mapOfStudents(): MutableMap<Int, Boolean> {
//        for (student in students.value!!){
//            checkedStudents.put(student.id,false)
//        }
//        if(!studentCourses.value.isNullOrEmpty()){
//            for(sc in studentCourses.value!!){
//                checkedStudents.replace(sc.student_id,true)
//            }
//        }
//        return checkedStudents
//    }

}
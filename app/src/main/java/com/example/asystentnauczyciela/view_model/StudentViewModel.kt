package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.model.repositories.StudentRepository



class StudentViewModel(application: Application): AndroidViewModel(application) {

    val students: LiveData<List<Student>> = SCDatabase.getDatabase(application).studentDao().allStudent()
    val studentCourses: LiveData<List<StudentCourse>> = SCDatabase.getDatabase(application).studentCourseDao().allSC()

    private val studentRepository:StudentRepository = StudentRepository(SCDatabase.getDatabase(application).studentDao())

    val navigationToEditStudent = MutableLiveData<Int?>()
    val navigationToEditMark = MutableLiveData<Int?>()



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
        navigationToEditStudent.postValue(student.id)

    }

    fun editMark(student: Student){
        navigationToEditMark.postValue(student.id)
    }

    fun onNavigationCompleted(){
        navigationToEditStudent.value = null
        navigationToEditMark.value = null
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
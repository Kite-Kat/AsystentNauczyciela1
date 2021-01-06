package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.model.repositories.StudentCourseRepository
import com.example.asystentnauczyciela.model.repositories.StudentRepository
import com.example.asystentnauczyciela.view.uimodel.CourseStudentItem
import kotlinx.coroutines.launch


class StudentViewModel(application: Application) : AndroidViewModel(application) {

    val students: LiveData<List<Student>> = SCDatabase.getDatabase(application).studentDao().allStudent()
    val studentItems: MutableLiveData<List<CourseStudentItem>> = MutableLiveData<List<CourseStudentItem>>()

    private val studentRepository: StudentRepository = StudentRepository(SCDatabase.getDatabase(application).studentDao())

    private val studentCourseRepository = StudentCourseRepository(SCDatabase.getDatabase(application).studentCourseDao())

    val navigation = MutableLiveData<Int?>()
    val checkedStudents = mutableMapOf<Int, Boolean>()

    fun setArgs(courseId: Int) {
        viewModelScope.launch {
            val checkedStudents = createCheckedStudentList(courseId)
            studentItems.value = checkedStudents
        }
    }

    private suspend fun createCheckedStudentList(courseId: Int): List<CourseStudentItem> {
        val studentCourses = studentCourseRepository.getStudentCourses(courseId)
        val checkedStudents = getCheckedStudents(studentCourses)
        return checkedStudents
    }

    fun addStudent(name: String, surname: String) {

        //musi byc wykonane asynchronicznie
        viewModelScope.launch {
            studentRepository.add(Student(id = 0, name = name, surname = surname))
        }

    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.delete(student)
        }


    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.update(student)
        }
    }

    fun editStudent(student: Student) {
        navigation.postValue(student.id)

    }

    fun onNavigationCompleted() {
        navigation.value = null
    }


    private fun getCheckedStudents(studentCourses: List<StudentCourse>): List<CourseStudentItem> {
        // 1. Mapowanie Student to Student

        val checkedStudents = mutableListOf<CourseStudentItem>()
        for (student in students.value!!) {
            checkedStudents.put(student.id, false)
            for (sc in studentCourses.value!!) {
                val courseStudentItem = if (isInDatabase) {
                    CourseStudentItem(student.id, student.name, student.surname, true)
                } else {
                    CourseStudentItem(student.id, student.name, student.surname, false)
                }
                checkedStudents.add(courseStudentItem)
            }
//            viewModelScope.launch {
//                val studentCourses = studentCourseRepository.getStudentCourses(courseId)
//
//            }
//            if (!studentCourses.value.isNullOrEmpty()) {
//
//            }
        }
        return checkedStudents
    }

    fun onStudentCheckedChangeListener(studentId: Int, checked: Boolean) {
        //ToDo update list to database

        viewModelScope.launch {
            val courseStudentItem = createCheckedStudentList()
            studentItems.value = courseStudentItem
        }
    }
}
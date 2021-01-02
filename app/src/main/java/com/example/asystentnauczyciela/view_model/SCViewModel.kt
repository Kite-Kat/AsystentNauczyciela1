package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.model.repositories.StudentCourseRepository
import kotlinx.coroutines.launch


class SCViewModel(application: Application):AndroidViewModel(application) {

    val students: LiveData<List<Student>> = SCDatabase.getDatabase(application).studentDao().allStudent()
    val courses: LiveData<List<Course>> = SCDatabase.getDatabase(application).courseDao().allCourses()
    private val StudentCourseRepository:StudentCourseRepository = StudentCourseRepository(SCDatabase.getDatabase(application).studentCourseDao())

    fun addSC(student_id: Int, course_id:Int){
        viewModelScope.launch {
            StudentCourseRepository.add(StudentCourse(id=0,student_id,course_id))
        }
    }

    fun deleteSC(SC: StudentCourse){
        viewModelScope.launch {
            StudentCourseRepository.delete(SC)
        }
    }


}
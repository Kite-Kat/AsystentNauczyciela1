package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application): AndroidViewModel(application) {

    val courses: LiveData<List<Course>>
    private val courseRepository: CourseRepository

    init{
        courses=SCDatabase.getDatabase(application).courseDao().allCourses()
        courseRepository= CourseRepository(SCDatabase.getDatabase(application).courseDao())
    }

    fun addCourse(name:String)
    {
        viewModelScope.launch {
            courseRepository.add(Course(id=0,name = name))
        }
    }

    fun deleteCourse(course: Course)
    {
        viewModelScope.launch {
            courseRepository.delete(course)
        }

    }

    fun updateCourse(course: Course){
        viewModelScope.launch {
            courseRepository.update(course)
        }
    }
}
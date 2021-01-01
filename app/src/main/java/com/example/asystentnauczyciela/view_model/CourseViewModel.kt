package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {

    val courses: LiveData<List<Course>> = SCDatabase.getDatabase(application).courseDao().allCourses()

    private val courseRepository: CourseRepository = CourseRepository(SCDatabase.getDatabase(application).courseDao())

    val navigation = MutableLiveData<Int?>()

    fun addCourse(name: String) {
        viewModelScope.launch {
            courseRepository.add(Course(id = 0, name = name))
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.delete(course)
        }

    }

    fun updateCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.update(course)
        }
    }

    fun editCourse(course: Course) {
        navigation.postValue(course.id)
    }

    fun onNavigationCompleted() {
        navigation.value = null
    }
}
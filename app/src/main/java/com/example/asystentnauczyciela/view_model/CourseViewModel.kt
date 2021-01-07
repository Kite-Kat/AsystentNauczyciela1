package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {

    val courses: LiveData<List<Course>> = SCDatabase.getDatabase(application).courseDao().allCourses()

    private val courseRepository: CourseRepository = CourseRepository(SCDatabase.getDatabase(application).courseDao())

    val navigationToEdit = MutableLiveData<Int?>()
    val navigationToMarks = MutableLiveData<Int?>()

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
        navigationToEdit.postValue(course.id)
    }

    fun editMark(course: Course){
        navigationToMarks.postValue(course.id)
    }


    fun onNavigationCompleted() {
        navigationToEdit.value = null
        navigationToMarks.value = null
    }

    fun getCourseById(courseID: Int): Course {
        var thisCourse: Course = Course(id=0,"Nie ma takiego kursu")
        if(courses.value !=null){
            for (course in courses.value!!){
                if(course.id == courseID)
                    return course


            }
        }

        return thisCourse
    }
}
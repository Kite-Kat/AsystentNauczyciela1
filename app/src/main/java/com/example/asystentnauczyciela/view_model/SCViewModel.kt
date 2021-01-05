package com.example.asystentnauczyciela.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse
import com.example.asystentnauczyciela.model.repositories.StudentCourseRepository
import kotlinx.coroutines.launch


class SCViewModel(application: Application):AndroidViewModel(application) {

    val studentsCourses : LiveData<List<StudentCourse>> = SCDatabase.getDatabase(application).studentCourseDao().allSC()
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

    fun addToSCDatabase(map: MutableMap<Int,Boolean>, courseID: Int){

        for (maps in map) {

            if (studentsCourses.value.isNullOrEmpty()) {
                if(maps.value) {
                    addSC(maps.key, courseID)
                    Log.d("baza",studentsCourses.value.isNullOrEmpty().toString())

                }
            }
            else {
                for (sc in studentsCourses.value!!) {
                    Log.d("baza",sc.student_id.toString())
                    if (!maps.value) {
                        if(maps.key == sc.student_id && courseID == sc.course_id){
                            deleteSC(sc)
                        }
                    }
                    else{
                        if(!(maps.key == sc.student_id && courseID == sc.course_id)){
                            addSC(maps.key, courseID)

                        }
                    }
                }
            }

        }

    }




}
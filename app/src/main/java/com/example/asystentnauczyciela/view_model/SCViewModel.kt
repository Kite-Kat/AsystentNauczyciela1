package com.example.asystentnauczyciela.view_model

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
    private val students: LiveData<List<Student>> = SCDatabase.getDatabase((application)).studentDao().allStudent()

    fun isConnectionInDB(idStudent:Int, idCourse: Int): Boolean{
        var value = false
        for (sc in studentsCourses.value!!){
            if(idStudent == sc.student_id && idCourse == sc.course_id){
                value = true
                return value

            }
        }
        return value
    }

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

    fun addStudentToSCDatabase(map: MutableMap<Int,Boolean>, courseID: Int){

        for (maps in map) {

            if (studentsCourses.value.isNullOrEmpty()) {
                if(maps.value) {
                    addSC(maps.key, courseID)
                }
            }
            else {
                    if (!maps.value) {
                        if(isConnectionInDB(maps.key, courseID)){
                            val thisSC = studentsCourses.value?.find {
                                x -> x.course_id == courseID && x.student_id == maps.key }
                            deleteSC(thisSC!!)
                        }
                    }
                    else{
                        if(!isConnectionInDB(maps.key, courseID)){
                            addSC(maps.key, courseID)

                        }
                    }
            }


        }

    }

    fun addCourseToSCDatabase(map: MutableMap<Int,Boolean>, studentID: Int){

        for (maps in map) {

            if (studentsCourses.value.isNullOrEmpty()) {
                if(maps.value) {
                    addSC( studentID, maps.key)
                }
            }
            else {

                    if (!maps.value) {
                        if(isConnectionInDB(studentID, maps.key)){
                            val thisSC = studentsCourses.value?.find {
                                x -> x.course_id == maps.key && x.student_id == studentID }
                            deleteSC(thisSC!!)
                        }
                    }
                    else{
                        if(!isConnectionInDB(studentID, maps.key)){
                            addSC( studentID, maps.key)

                        }
                    }

                }

        }

    }


}
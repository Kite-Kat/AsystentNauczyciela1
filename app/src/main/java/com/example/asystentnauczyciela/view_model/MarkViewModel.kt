package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.launch
import java.util.*

class MarkViewModel(application: Application):AndroidViewModel(application) {

    val marks: LiveData<List<Mark>>
    private val markRepository: MarkRepository

    val navigationToEdit = MutableLiveData<Int?>()


    init{
        marks= SCDatabase.getDatabase(application).markDao().allmarks()
        markRepository= MarkRepository(SCDatabase.getDatabase(application).markDao())
    }

    fun addMark(student_id:Int, course_id:Int, mark: Double, date: String,note:String)
    {
        viewModelScope.launch {
            markRepository.add(Mark(id=0,student_id, course_id,mark,date,note))
        }
    }

    fun deleteMark(mark: Mark)
    {
        viewModelScope.launch {
            markRepository.delete(mark)
        }

    }

    fun updateMark(mark: Mark){

        viewModelScope.launch {
            markRepository.update(mark)
        }

    }

    fun editMark(mark:Mark){
        navigationToEdit.postValue(mark.id)
    }

    fun onNavigationCompleted(){
        navigationToEdit.value = null
    }

    fun findMarksForSC(studentID: Int, courseID: Int):List<Mark>{
        var markList = mutableListOf<Mark>()
        if (marks.value != null)
        for (mark in marks.value!!){
            if(mark.course_id == courseID && mark.student_id==studentID){
                markList.add(mark)
            }
        }
        return markList
    }

    fun getMarkById(markID: Int): Mark {
        var thisMark: Mark = Mark(id=0,0,0,0.0,"Brak","Nie ma takiej oceny")
        if(marks.value !=null){
            for (mark in marks.value!!){
                if(mark.id == markID)
                    return mark


            }
        }

        return thisMark
    }

    fun changeMarkToSpinnerPosition(mark:Double):Int{
        var index = 0

        when(mark) {
            1.0 -> index = 0
            1.5 -> index = 1
            2.0 -> index = 2
            2.5 -> index = 3
            3.0 -> index = 4
            3.5 -> index = 5
            4.0 -> index = 6
            4.5 -> index = 7
            5.0 -> index = 8
            5.5 -> index = 9
            6.0 -> index = 10
        }

        return index
    }
}
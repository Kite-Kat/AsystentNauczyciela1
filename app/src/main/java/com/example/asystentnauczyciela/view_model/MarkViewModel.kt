package com.example.asystentnauczyciela.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.repositories.CourseRepository
import com.example.asystentnauczyciela.model.repositories.MarkRepository
import kotlinx.coroutines.launch
import java.util.*

class MarkViewModel(application: Application):AndroidViewModel(application) {

    val marks: LiveData<List<Mark>>
    private val markRepository: MarkRepository

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
}
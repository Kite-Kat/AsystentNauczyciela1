package com.example.asystentnauczyciela.view_model

import android.media.AudioMetadata
import android.os.Build
import android.text.BoringLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.view.COURSE_ID
import com.example.asystentnauczyciela.view.CourseEditFragment


class AddStudentListAdapter(private val viewModelStudent: StudentViewModel): ListAdapter<Student, AddStudentListAdapter.StudentHolder>(AddStudentDiff){

    inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_student_one_row, parent, false)
                return StudentHolder(view)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var checkbox = holder.itemView.findViewById<CheckBox>(R.id.checkBoxForStudentName)

        var checkedStudents = viewModelStudent.mapOfStudents()



        val student = getItem(position)




        if( checkedStudents[student.id]!!){
            Log.d("warunek zaznaczenia","jestem")
            checkbox.isChecked = true
        }
        else{
            checkbox.isChecked = false
            Log.d("warunek zaznaczenia","nie ma mnie")
        }

        checkbox.text = student.name +" "+ student.surname

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkedStudents.replace(student.id, true)
//                Log.d("zaznaczony",checkedStudents[student.id].toString())
//                Log.d("mapaAdapter",checkedStudents.toString())

            }
            else{
                checkedStudents.replace(student.id, false)
//                Log.d("zaznaczony",checkedStudents[student.id].toString())
//                Log.d("mapaAdapter",checkedStudents.toString())
            }
        }

    }


}

object AddStudentDiff: DiffUtil.ItemCallback<Student>(){
    override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

}
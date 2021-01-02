package com.example.asystentnauczyciela.view_model

import android.media.AudioMetadata
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
var checkedStudents = mutableMapOf(Pair(Int,Boolean))
class AddStudentListAdapter(private val viewModelStudent: StudentViewModel): ListAdapter<Student, AddStudentListAdapter.StudentHolder>(AddStudentDiff){

    inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_student_one_row, parent, false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var checkbox = holder.itemView.findViewById<CheckBox>(R.id.checkBoxForStudentName)

        val student = getItem(position)

        checkbox.text = student.name +" "+ student.surname

        checkbox.setOnClickListener {


        }

    }


}



object AddStudentDiff: DiffUtil.ItemCallback<Student>(){
    override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

}
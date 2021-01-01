package com.example.asystentnauczyciela.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student

class StudentListAdapter(private val viewModelStudent: StudentViewModel): ListAdapter<Student, StudentListAdapter.StudentHolder>(StudentDiff) {

    inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_one_row, parent, false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var textViewStudentName = holder.itemView.findViewById<TextView>(R.id.textViewStudentName)
        var textViewStudentSurname =
            holder.itemView.findViewById<TextView>(R.id.textViewStudentSurname)


        val student = getItem(position)

        val buttonDStudent =
            holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonDelStudent)
        val buttonEditStudent = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonEditStudent)

        buttonDStudent.setOnClickListener {
            viewModelStudent.deleteStudent(student)
            notifyDataSetChanged()
        }

        buttonEditStudent.setOnClickListener {
            viewModelStudent.editStudent(student)
        }
    }
}

    object StudentDiff: DiffUtil.ItemCallback<Student>(){
        override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

    }

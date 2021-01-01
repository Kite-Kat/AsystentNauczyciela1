package com.example.asystentnauczyciela.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student

class StudentListAdapter(var students: LiveData<List<Student>>, var viewModelStudent: StudentViewModel): RecyclerView.Adapter<StudentListAdapter.StudentHolder>() {

    inner class StudentHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.student_list_one_row,parent,false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var textViewStudentName= holder.itemView.findViewById<TextView>(R.id.textViewStudentName)
        var textViewStudentSurname= holder.itemView.findViewById<TextView>(R.id.textViewStudentSurname)


        textViewStudentName.text=students.value?.get(position)?.name
        textViewStudentSurname.text=students.value?.get(position)?.surname

        val buttonDStudent = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonDelStudent)

        buttonDStudent.setOnClickListener{
            viewModelStudent.deleteStudent(students.value!!.get(position))
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return students.value?.size?:0
    }
}
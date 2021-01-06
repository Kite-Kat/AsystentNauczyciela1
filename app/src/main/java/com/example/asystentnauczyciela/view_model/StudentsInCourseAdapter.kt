package com.example.asystentnauczyciela.view_model

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse

class StudentsInCourseAdapter( private val courseId: Int, private val connections: LiveData<List<StudentCourse>>):ListAdapter<Student, StudentsInCourseAdapter.StudentHolder>(StudentsInCourseDiff){

    inner class StudentHolder(view: View): RecyclerView.ViewHolder(view)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.student_list_one_row, parent, false)
        return StudentHolder(view)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        var textViewStudentName = holder.itemView.findViewById<TextView>(R.id.textViewStudentName)
        var textViewStudentSurname = holder.itemView.findViewById<TextView>(R.id.textViewStudentSurname)

        val buttonDStudent = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonDelStudent)
        val buttonEditStudent = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonEditStudent)
        val student = getItem(position)

        buttonDStudent.isVisible = false

        val thisStudentCourse = connections.value?.find {
            x -> x.course_id == courseId && x.student_id == student.id }
        if (thisStudentCourse != null){
            Log.d("im IN", "potwierdzam")
            textViewStudentName.text = student.name
            textViewStudentSurname.text = student.surname
        }
        else{
            textViewStudentName.isVisible = false
            textViewStudentSurname.isVisible = false
            buttonEditStudent.isVisible = false

        }

    }




}

object StudentsInCourseDiff: DiffUtil.ItemCallback<Student>(){
    override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

}
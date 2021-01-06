package com.example.asystentnauczyciela.view_model

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view.uimodel.CourseStudentItem


class AddStudentListAdapter(private val viewModelStudent: StudentViewModel) :
    ListAdapter<CourseStudentItem, AddStudentListAdapter.StudentHolder>(AddStudentDiff) {

    inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.add_student_one_row, parent, false)
        return StudentHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val checkbox = holder.itemView.findViewById<CheckBox>(R.id.checkBoxForStudentName)

        val student = getItem(position)
        checkbox.isChecked = student.isChecked

        checkbox.text = student.name + " " + student.surname

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModelStudent.onStudentCheckedChangeListener(student.id, isChecked)
        }
    }
}

object AddStudentDiff : DiffUtil.ItemCallback<CourseStudentItem>() {
    override fun areItemsTheSame(oldItem: CourseStudentItem, newItem: CourseStudentItem) = oldItem === newItem

    override fun areContentsTheSame(oldItem: CourseStudentItem, newItem: CourseStudentItem) = oldItem == newItem

}
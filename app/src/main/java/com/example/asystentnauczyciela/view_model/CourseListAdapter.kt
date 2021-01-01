package com.example.asystentnauczyciela.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Course

class CourseListAdapter(private val viewModelCourse: CourseViewModel) : ListAdapter<Course, CourseListAdapter.CourseHolder>(CourseDiff) {

    inner class CourseHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list_one_row, parent, false)
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val textViewName = holder.itemView.findViewById<TextView>(R.id.textViewCourseName)

        val course = getItem(position)
        textViewName.text = course.name

        val buttonD = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonDelCourse)
        val buttonEdit = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonEditCourse)
        val editTextCourse = holder.itemView.findViewById<EditText>(R.id.CourseEditName)

        buttonD.setOnClickListener {
            viewModelCourse.deleteCourse(course)
            notifyDataSetChanged()
        }

        buttonEdit.setOnClickListener {
            viewModelCourse.editCourse(course)
        }
    }
}

object CourseDiff : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course) = oldItem === newItem

    override fun areContentsTheSame(oldItem: Course, newItem: Course) = oldItem == newItem

}
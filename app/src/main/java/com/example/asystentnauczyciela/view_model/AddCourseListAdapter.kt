package com.example.asystentnauczyciela.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.Student

class AddCourseListAdapter(private val viewModelCourse: CourseViewModel):ListAdapter<Course, AddCourseListAdapter.CourseHolder>(CourseDiff){

    inner class CourseHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCourseListAdapter.CourseHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_course_one_row, parent, false)
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: AddCourseListAdapter.CourseHolder, position: Int) {
        var checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkBoxForCourseName)
        val course = getItem(position)

        checkBox.text = course.name
    }
}

object AddCourseDiff: DiffUtil.ItemCallback<Student>(){
    override fun areItemsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Student, newItem: Student) = oldItem == newItem

}
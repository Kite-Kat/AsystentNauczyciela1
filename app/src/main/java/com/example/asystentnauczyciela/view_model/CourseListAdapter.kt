package com.example.asystentnauczyciela.view_model

import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.view.CourseEditFragment
import java.nio.file.Files.delete

class CourseListAdapter (var courses : LiveData<List<Course>>, var viewModelCourse: CourseViewModel): RecyclerView.Adapter<CourseListAdapter.CourseHolder>() {

    inner class CourseHolder(view: View):RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list_one_row,parent,false)
        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        var textViewName= holder.itemView.findViewById<TextView>(R.id.textViewCourseName)


        val course = courses.value?.get(position)
        textViewName.text=courses.value?.get(position)?.name

        val buttonD = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonDelCourse)
        val buttonEdit = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonEditCourse)
        val editTextCourse = holder.itemView.findViewById<EditText>(R.id.CourseEditName)

        buttonD.setOnClickListener{
            viewModelCourse.deleteCourse(courses.value!!.get(position))
            notifyDataSetChanged()
        }

        buttonEdit.setOnClickListener {
            editTextCourse.setText("tekst")
            //view->view.findNavController().navigate(R.id.action_courseFragment_to_courseEditFragment)



        }
    }

    override fun getItemCount(): Int {
        return courses.value?.size?:0
    }
}
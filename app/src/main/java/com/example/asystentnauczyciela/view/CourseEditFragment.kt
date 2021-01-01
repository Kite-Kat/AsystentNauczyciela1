package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.view_model.CourseListAdapter
import com.example.asystentnauczyciela.view_model.CourseViewModel
import kotlinx.android.synthetic.main.fragment_course_edit.*
import android.app.Application as application

const val COURSE_ID = "courseId"
private lateinit var viewModelCourse: CourseViewModel


class CourseEditFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val courseId = it.getInt(COURSE_ID)
            //Toast.makeText(requireContext(), courseId.toString(), Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            val courseId = it.getInt(COURSE_ID)

            val courses = viewModelCourse.courses.value!!
            for (course in courses){

                if ( course.id == courseId){
                    val courseName = course.name
                    CourseEditName.setText(courseName)
                }
            }



        }

    }
}
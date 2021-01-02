package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.view_model.AddStudentListAdapter
import com.example.asystentnauczyciela.view_model.CourseListAdapter
import com.example.asystentnauczyciela.view_model.CourseViewModel
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_course_edit.*
import android.app.Application as application

const val COURSE_ID = "courseId"
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelStudent: StudentViewModel


class CourseEditFragment : Fragment() {

    private lateinit var addStudentAdapter: AddStudentListAdapter
    private lateinit var addStudentLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

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

        addStudentLayoutManager = LinearLayoutManager(context)

        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)

        addStudentAdapter = AddStudentListAdapter(viewModelStudent)


        viewModelStudent.students.observe(viewLifecycleOwner,{
            addStudentAdapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var thisCourse:Course

        recyclerView = RecyclerViewCheckBoxStudent.apply{
            this.layoutManager = addStudentLayoutManager
            this.adapter = addStudentAdapter
        }

        arguments?.let{
            val courseId = it.getInt(COURSE_ID)

            val courses = viewModelCourse.courses.value!!
            for (course in courses){

                if ( course.id == courseId){
                    val courseName = course.name
                    CourseEditName.setText(courseName)
                }
            }
            buttonSaveEditCourse.setOnClickListener {
                thisCourse = Course(courseId,CourseEditName.text.toString())
                viewModelCourse.updateCourse(thisCourse)
                view.findNavController().navigate(R.id.action_courseEditFragment_to_courseFragment)
            }
        }



    }
}
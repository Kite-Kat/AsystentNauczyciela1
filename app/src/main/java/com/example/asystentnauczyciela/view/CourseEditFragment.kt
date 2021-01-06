package com.example.asystentnauczyciela.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Course
import com.example.asystentnauczyciela.view_model.*
import kotlinx.android.synthetic.main.fragment_course_edit.*

const val COURSE_ID = "courseId"
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelSc: SCViewModel



class CourseEditFragment : Fragment() {

    private lateinit var addStudentAdapter: AddStudentListAdapter
    private lateinit var addStudentLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        arguments?.let {
            val courseId = it.getInt(COURSE_ID)


            addStudentLayoutManager = LinearLayoutManager(context)

            viewModelStudent =
                    ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
            viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
            viewModelSc = ViewModelProvider(requireActivity()).get(SCViewModel::class.java)

            // Adapter i obserwatorzy LiveData:
            addStudentAdapter = AddStudentListAdapter(
                    viewModelStudent.students,
                    viewModelSc.studentsCourses,
                    courseId
            )
            viewModelStudent.students.observe(viewLifecycleOwner,
                    { addStudentAdapter.notifyDataSetChanged() })
            viewModelSc.studentsCourses.observe(
                    viewLifecycleOwner,
                    { addStudentAdapter.notifyDataSetChanged() })
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
                viewModelCourse.updateCourse(Course(courseId,CourseEditName.text.toString()))
                viewModelSc.addStudentToSCDatabase(addStudentAdapter.checkedStudents, courseId)

                view.findNavController().navigate(R.id.action_courseEditFragment_to_courseFragment)
                


            }

        }

    }

}


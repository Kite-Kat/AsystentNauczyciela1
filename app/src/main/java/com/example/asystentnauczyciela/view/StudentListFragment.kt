package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.CourseViewModel
import com.example.asystentnauczyciela.view_model.SCViewModel
import com.example.asystentnauczyciela.view_model.StudentViewModel
import com.example.asystentnauczyciela.view_model.StudentsInCourseAdapter
import kotlinx.android.synthetic.main.fragment_student_list.*

const val COURSEID = "course_ID"
private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelSc: SCViewModel
private lateinit var viewModelCourse: CourseViewModel



class StudentListFragment : Fragment() {

    private lateinit var studentListAdapter: StudentsInCourseAdapter
    private lateinit var studentListLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

           }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let{
            val courseID = it.getInt(COURSEID)

            studentListLayoutManager = LinearLayoutManager(context)

            viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
            viewModelSc = ViewModelProvider(requireActivity()).get(SCViewModel::class.java)
            viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)

            studentListAdapter = StudentsInCourseAdapter(courseID,viewModelSc.studentsCourses)

            viewModelStudent.students.observe(viewLifecycleOwner, {
                studentListAdapter.submitList(it)
            })

            viewModelSc.studentsCourses.observe(viewLifecycleOwner,{

            })

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Wyświetlamy recyclerView
        recyclerView = RecyclerViewStudentsForCourse.apply {
            this.layoutManager = studentListLayoutManager
            this.adapter = studentListAdapter
        }
    }



}
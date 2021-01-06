package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class CourseEditFragment : Fragment() {

    private lateinit var addStudentAdapter: AddStudentListAdapter
    private lateinit var addStudentLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    private lateinit var viewModelCourse: CourseViewModel
    private lateinit var viewModelStudent: StudentViewModel
    private lateinit var viewModelSc: SCViewModel

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
        viewModelSc = ViewModelProvider(requireActivity()).get(SCViewModel::class.java)

        addStudentAdapter = AddStudentListAdapter(viewModelStudent)

        viewModelStudent.studentItems.observe(viewLifecycleOwner,{
            addStudentAdapter.submitList(it)
        })

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
            viewModelStudent.setArgs(courseId)

            val courses = viewModelCourse.courses.value!!
            for (course in courses){

                if ( course.id == courseId){
                    val courseName = course.name
                    CourseEditName.setText(courseName)
                }
            }
            buttonSaveEditCourse.setOnClickListener {
                viewModelCourse.updateCourse(Course(courseId,CourseEditName.text.toString()))
                viewModelSc.addToSCDatabase(viewModelStudent.checkedStudents, courseId)

                Log.d("Co przekazano", viewModelStudent.checkedStudents[viewModelStudent.students.value!![0].id].toString())
                view.findNavController().navigate(R.id.action_courseEditFragment_to_courseFragment)

            }

        }
        
    }
}
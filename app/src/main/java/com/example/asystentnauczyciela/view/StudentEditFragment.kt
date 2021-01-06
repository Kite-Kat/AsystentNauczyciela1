package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.view_model.*
import kotlinx.android.synthetic.main.fragment_course_edit.*
import kotlinx.android.synthetic.main.fragment_student_edit.*

const val STUDENT_ID = "studentId"
private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelSc: SCViewModel

class StudentEditFragment : Fragment() {

    private lateinit var addCourseAdapter: AddCourseListAdapter
    private lateinit var addCourseLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        arguments?.let{

        val studentId = it.getInt(STUDENT_ID)

        addCourseLayoutManager = LinearLayoutManager(context)

        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        viewModelSc = ViewModelProvider(requireActivity()).get(SCViewModel::class.java)

        // Adapter i obserwatorzy
        addCourseAdapter = AddCourseListAdapter(viewModelCourse.courses, viewModelSc.studentsCourses, studentId)

        viewModelCourse.courses.observe(viewLifecycleOwner,{
            addCourseAdapter.notifyDataSetChanged()
        })
        viewModelSc.studentsCourses.observe( viewLifecycleOwner,{
            addCourseAdapter.notifyDataSetChanged()
        })

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_edit, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        recyclerView = RecyclerViewCheckBoxCourse.apply{
            this.layoutManager = addCourseLayoutManager
            this.adapter = addCourseAdapter
        }

        arguments?.let {
            val studentId = it.getInt(STUDENT_ID)

            val students = viewModelStudent.students.value!!
            for (student in students) {

                if (student.id == studentId) {
                    val studentName = student.name
                    val studentSurname = student.surname
                    StudentEditName.setText(studentName)
                    StudentEditSurname.setText(studentSurname)
                }
            }

            buttonSaveEditStudent.setOnClickListener {
                viewModelStudent.updateStudent(Student(studentId,StudentEditName.text.toString(),StudentEditSurname.text.toString()))
                viewModelSc.addCourseToSCDatabase(addCourseAdapter.checkedCourses, studentId)

                view.findNavController().navigate(R.id.action_studentEditFragment_to_studentFragment)

            }

        }

    }

    companion object { fun newInstance() = StudentEditFragment() }
}
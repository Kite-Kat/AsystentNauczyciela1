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
import com.example.asystentnauczyciela.view_model.AddCourseListAdapter
import com.example.asystentnauczyciela.view_model.AddStudentListAdapter
import com.example.asystentnauczyciela.view_model.CourseViewModel
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_course_edit.*
import kotlinx.android.synthetic.main.fragment_student_edit.*

const val STUDENT_ID = "studentId"
private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelCourse: CourseViewModel

class StudentEditFragment : Fragment() {

    private lateinit var addCourseAdapter: AddCourseListAdapter
    private lateinit var addCourseLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val studentId = it.getString(STUDENT_ID)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addCourseLayoutManager = LinearLayoutManager(context)

        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)

        addCourseAdapter = AddCourseListAdapter(viewModelCourse)

        viewModelCourse.courses.observe(viewLifecycleOwner,{
            addCourseAdapter.submitList(it)
        })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var thisStudent: Student


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
                thisStudent = Student(studentId,StudentEditName.text.toString(),StudentEditSurname.text.toString())
                viewModelStudent.updateStudent(thisStudent)
                view.findNavController().navigate(R.id.action_studentEditFragment_to_studentFragment)

            }

        }



    }
}
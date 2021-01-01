package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.CourseViewModel
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_course_edit.*
import kotlinx.android.synthetic.main.fragment_student_edit.*

const val STUDENT_ID = "studentId"
private lateinit var viewModelStudent: StudentViewModel

class StudentEditFragment : Fragment() {

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
        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        }
    }
}
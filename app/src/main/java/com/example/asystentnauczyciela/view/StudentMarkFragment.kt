package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.*
import kotlinx.android.synthetic.main.fragment_student_mark.*


const val COURSE_id = "course_id"
 const val student_ID = "student_id"
private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelSc: SCViewModel
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelMark: MarkViewModel


class StudentMarkFragment : Fragment() {

    private lateinit var MarksListAdapter: MarksListAdapter
    private lateinit var studentListLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_mark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            val studentID = it.getInt(student_ID)
            val courseID = it.getInt(COURSE_id)


            textViewAverage.text = studentID.toString()+courseID.toString()

        }

        context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.spinnerMarkElements,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerWithMarkses.adapter = adapter
            }
        }
    }


}
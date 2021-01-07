package com.example.asystentnauczyciela.view

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.view_model.CourseViewModel
import com.example.asystentnauczyciela.view_model.MarkViewModel
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_student_edit_mark.*
import kotlinx.android.synthetic.main.fragment_student_mark.*
import java.text.SimpleDateFormat
import java.util.*

const val MARK_ID = "mark_id"
private lateinit var viewModelMark: MarkViewModel
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelStudent: StudentViewModel


class StudentEditMarkFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelMark = ViewModelProvider(requireActivity()).get(MarkViewModel::class.java)
        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)

        viewModelMark.marks.observe(viewLifecycleOwner,{

        })
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_edit_mark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {context->
            val list = mutableListOf(
                    "2",
                    "3",
                    "3.5",
                    "4",
                    "4.5",
                    "5"
            )
            val adapter: ArrayAdapter<String> = object: ArrayAdapter<String>(
                    context, android.R.layout.simple_spinner_dropdown_item,
                    list
            ){
                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view: TextView = super.getDropDownView(position, convertView, parent) as TextView
                    view.setTypeface(Typeface.MONOSPACE, Typeface.BOLD)
                    view.setTextColor(Color.parseColor("#C58648"))

                    if( position==spinnerEdycjaOceny.selectedItemPosition){
                        view.background= ColorDrawable(Color.parseColor("#80C58648"))
                    }
                    return view
                }

            }
            spinnerEdycjaOceny.adapter=adapter
        }


        arguments?.let{
            val markID = it.getInt(MARK_ID)
            val mark = viewModelMark.getMarkById(markID)
            val student = viewModelStudent.getStudentById(mark.student_id)
            val course = viewModelCourse.getCourseById(mark.course_id)

            textViewEdycjaOcenyStudent.text = student.name + " " + student.surname
            textViewEdycjaOcenyPrzedmiot.text = course.name
            editTextEdycjaOcenyNotatka.setText(mark.note)
            spinnerEdycjaOceny.setSelection(viewModelMark.changeMarkToSpinnerPosition(mark.mark))

            buttonEdycjaOcenyZapisz.setOnClickListener {
                viewModelMark.updateMark(Mark(mark.id,student.id,course.id,spinnerEdycjaOceny.selectedItem.toString().toDouble(),
                                        SimpleDateFormat("dd-MM-yyyy").format(Date()), editTextEdycjaOcenyNotatka.text.toString()))
                val bundle = bundleOf(COURSE_id to mark.course_id, student_ID to mark.student_id)
                findNavController().navigate(R.id.action_studentEditMarkFragment_to_studentMarkFragment,bundle)
            }

        }







    }

}
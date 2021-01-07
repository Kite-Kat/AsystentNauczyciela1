package com.example.asystentnauczyciela.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.view_model.*
import kotlinx.android.synthetic.main.fragment_student_mark.*
import java.text.SimpleDateFormat
import java.util.*


const val COURSE_id = "course_id"
 const val student_ID = "student_id"
private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelSc: SCViewModel
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelMark: MarkViewModel


class StudentMarkFragment : Fragment() {

    private lateinit var MarksListAdapter: MarksListAdapter
    private lateinit var markListLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView




    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        arguments?.let{
            val studentID = it.getInt(student_ID)
            val courseID = it.getInt(COURSE_id)

            markListLayoutManager = LinearLayoutManager(context)
            viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
            viewModelSc = ViewModelProvider(requireActivity()).get(SCViewModel::class.java)
            viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
            viewModelMark = ViewModelProvider(requireActivity()).get(MarkViewModel::class.java)

            MarksListAdapter = MarksListAdapter(viewModelMark, studentID, courseID)

            viewModelMark.marks.observe(viewLifecycleOwner,{
                MarksListAdapter.submitList(it)
                textViewAverage.text = "Srednia ocen: "+viewModelMark.calculateAverageForStudent(courseID,studentID).toString()
            })



            viewModelMark.navigationToEdit.observe(viewLifecycleOwner,{markID :Int?->
                markID?.let{markID:Int ->
                val bundle = bundleOf(MARK_ID to markID)
                    findNavController().navigate(R.id.action_studentMarkFragment_to_studentEditMarkFragment,bundle)
                    viewModelMark.onNavigationCompleted()
                }

            })

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_mark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = RecyclerViewStudentMarks.apply {
            this.layoutManager = markListLayoutManager
            this.adapter = MarksListAdapter
        }
        arguments?.let{
            val studentID = it.getInt(student_ID)
            val courseID = it.getInt(COURSE_id)

            textViewCourseMarkFragment.text = viewModelCourse.getCourseById(courseID).name
            textViewStudentNameMarkFragment.text = viewModelStudent.getStudentById(studentID).name
            textViewStudentSurnameMarkFragment.text = viewModelStudent.getStudentById(studentID).surname



            textViewAverage.text = "Srednia ocen: "+viewModelMark.calculateAverageForStudent(courseID,studentID).toString()




            buttonAddMark.setOnClickListener {
                viewModelMark.addMark(studentID,courseID,spinnerWithMarkses.selectedItem.toString().toDouble(), SimpleDateFormat("dd-MM-yyyy").format(Date()),editTextNoteForMarks.text.toString())
                editTextNoteForMarks.setText("")
                view.hideKeyboard()

            }

            textViewCourseMarkFragment.text = viewModelCourse.getCourseById(courseID).name
           textViewStudentNameMarkFragment.text = viewModelStudent.getStudentById(studentID).name
           textViewStudentSurnameMarkFragment.text = viewModelStudent.getStudentById(studentID).surname
        }

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
                    val view:TextView = super.getDropDownView(position, convertView, parent) as TextView
                    view.setTypeface(Typeface.MONOSPACE,Typeface.BOLD)
                    view.setTextColor(Color.parseColor("#C58648"))

                    if( position==spinnerWithMarkses.selectedItemPosition){
                        view.background=ColorDrawable(Color.parseColor("#80C58648"))
                    }
                    return view
                }

            }

            spinnerWithMarkses.adapter=adapter
        }


    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
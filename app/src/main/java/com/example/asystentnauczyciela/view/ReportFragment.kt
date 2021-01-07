package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.*
import kotlinx.android.synthetic.main.fragment_report.*
import java.text.SimpleDateFormat
import java.util.*

private lateinit var viewModelStudent: StudentViewModel
private lateinit var viewModelCourse: CourseViewModel
private lateinit var viewModelMark: MarkViewModel
class ReportFragment : Fragment() {

    private lateinit var reportAdapter: ReportAdapter
    private lateinit var reportLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reportLayoutManager = LinearLayoutManager(context)
        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        viewModelMark = ViewModelProvider(requireActivity()).get(MarkViewModel::class.java)

        reportAdapter = ReportAdapter(viewModelStudent, viewModelCourse)

        viewModelMark.marks.observe(viewLifecycleOwner,{
            reportAdapter.submitList(it)
        })
        viewModelStudent.students.observe(viewLifecycleOwner,{

        })

        viewModelCourse.courses.observe(viewLifecycleOwner,{

        })


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = RecyclerViewReport.apply{
            this.layoutManager = reportLayoutManager
            this.adapter = reportAdapter
        }

        textViewReportDate.text = SimpleDateFormat("dd-MM-yyyy").format(Date())
    }

}
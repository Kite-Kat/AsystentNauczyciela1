package com.example.asystentnauczyciela.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.CourseListAdapter
import com.example.asystentnauczyciela.view_model.CourseViewModel
import kotlinx.android.synthetic.main.fragment_course.*


private lateinit var viewModelCourse: CourseViewModel

class CourseFragment : Fragment() {

    private lateinit var courseAdapter: CourseListAdapter
    private lateinit var courseLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        courseLayoutManager = LinearLayoutManager(context)

        viewModelCourse = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)

        courseAdapter = CourseListAdapter(viewModelCourse)

        viewModelCourse.courses.observe(viewLifecycleOwner, {
            courseAdapter.submitList(it)
        })

        viewModelCourse.navigationToEdit.observe(viewLifecycleOwner) { courseId: Int? ->

            courseId?.let { courseId: Int ->
                val bundle = bundleOf(COURSE_ID to courseId)
                findNavController().navigate(R.id.action_courseFragment_to_courseEditFragment, bundle)

                viewModelCourse.onNavigationCompleted()
            }

        }

        viewModelCourse.navigationToMarks.observe(viewLifecycleOwner){courseId: Int? ->
            courseId?.let{ courseId:Int->
                val bundle = bundleOf(COURSEID to courseId)
                findNavController().navigate(R.id.action_courseFragment_to_studentListFragment, bundle)

                viewModelCourse.onNavigationCompleted()



            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Wyświetlamy recycler view
        recyclerView = RecyclerViewCourses.apply {
            this.layoutManager = courseLayoutManager
            this.adapter = courseAdapter
        }
        buttonAddCourse.setOnClickListener { viewModelCourse.addCourse(CourseNameLabel.text.toString())
        view.hideKeyboard() }



    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
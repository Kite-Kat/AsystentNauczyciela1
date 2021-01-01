package com.example.asystentnauczyciela.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.CourseListAdapter
import com.example.asystentnauczyciela.view_model.CourseViewModel
import kotlinx.android.synthetic.main.fragment_course.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModelCourse: CourseViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [CourseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var courseAdapter: CourseListAdapter
    private lateinit var courseLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

        viewModelCourse.navigation.observe(viewLifecycleOwner) { courseId: Int? ->

            courseId?.let { courseId: Int ->
                val bundle = bundleOf(COURSE_ID to courseId)
                findNavController().navigate(R.id.action_courseFragment_to_courseEditFragment, bundle)
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
        buttonAddCourse.setOnClickListener { viewModelCourse.addCourse(CourseNameLabel.text.toString()) }
        buttonShowCourses.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_courseFragment_to_courseListFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CourseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CourseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
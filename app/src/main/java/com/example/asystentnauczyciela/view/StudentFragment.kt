package com.example.asystentnauczyciela.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.view_model.CourseListAdapter
import com.example.asystentnauczyciela.view_model.CourseViewModel
import com.example.asystentnauczyciela.view_model.StudentListAdapter
import com.example.asystentnauczyciela.view_model.StudentViewModel
import kotlinx.android.synthetic.main.fragment_course.*
import kotlinx.android.synthetic.main.fragment_student.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewModelStudent:StudentViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [StudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var studentAdapter: StudentListAdapter
    private lateinit var studentLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        studentLayoutManager = LinearLayoutManager(context)

        viewModelStudent= ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)

        studentAdapter = StudentListAdapter(viewModelStudent)

        viewModelStudent.students.observe(viewLifecycleOwner, Observer {
            studentAdapter.submitList(it)

        })

        viewModelStudent.navigation.observe(viewLifecycleOwner){studentId: Int? ->

            studentId?.let{ studentId:Int ->
                val bundle = bundleOf(STUDENT_ID to studentId)
                findNavController().navigate(R.id.action_studentFragment_to_studentEditFragment ,bundle)
                viewModelStudent.onNavigationCompleted()

            }

        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Wyświetlamy recycler view
        recyclerView = RecyclerViewStudents.apply {
            this.layoutManager = studentLayoutManager
            this.adapter = studentAdapter
        }
        buttonAddStudent.setOnClickListener{viewModelStudent.addStudent(StudentNameLabel.text.toString(), StudentSurnameLabel.text.toString())}
        buttonShowStudents.setOnClickListener { view->view.findNavController().navigate(R.id.action_studentFragment_to_studentListFragment) }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
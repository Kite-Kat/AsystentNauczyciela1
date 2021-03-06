package com.example.asystentnauczyciela.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
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

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        studentLayoutManager = LinearLayoutManager(context)

        viewModelStudent = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)

        studentAdapter = StudentListAdapter(viewModelStudent)

        viewModelStudent.students.observe(viewLifecycleOwner, Observer {
            studentAdapter.submitList(it)

        })

        viewModelStudent.navigationToEditStudent.observe(viewLifecycleOwner) { studentId: Int? ->

            studentId?.let { studentId: Int ->
                val bundle = bundleOf(STUDENT_ID to studentId)
                findNavController().navigate(R.id.action_studentFragment_to_studentEditFragment, bundle)
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
        buttonAddStudent.setOnClickListener {
            val thisStudent = viewModelStudent.students.value?.find {
                x -> x.name == StudentNameLabel.text.toString() && x.surname == StudentSurnameLabel.text.toString() }
            if(StudentNameLabel.text.isEmpty() && StudentSurnameLabel.text.isEmpty()){

                Toast.makeText(context, "Imię i nazwisko studenta musi być uzupełnione",Toast.LENGTH_SHORT).show()
            }
            else if (thisStudent != null){
                Toast.makeText(context, "Taki student jest już zapisany",Toast.LENGTH_SHORT).show()
            }
            else{
                viewModelStudent.addStudent(StudentNameLabel.text.toString(), StudentSurnameLabel.text.toString())
                StudentNameLabel.setText("")
                StudentSurnameLabel.setText("")
            }


            view.hideKeyboard()}

    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
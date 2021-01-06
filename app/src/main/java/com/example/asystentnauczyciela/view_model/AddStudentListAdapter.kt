package com.example.asystentnauczyciela.view_model

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Student
import com.example.asystentnauczyciela.model.StudentCourse

// Dwa parametry: studenci i studentkursy; klasa dziedziczy po czymś trochę innym niż wcześniej...
class AddStudentListAdapter(var students: LiveData<List<Student>>,
                            var connections: LiveData<List<StudentCourse>>,
                            var courseId:Int): RecyclerView.Adapter<AddStudentListAdapter.StudentHolder>(){


    var checkedStudents = mutableMapOf<Int,Boolean>()


    inner class StudentHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_student_one_row, parent, false)
        return StudentHolder(view)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {

        // Przypisanie danych:
        val checkbox = holder.itemView.findViewById<CheckBox>(R.id.checkBoxForStudentName)
        checkbox.text = "${students.value?.get(position)?.surname} ${students.value?.get(position)?.name}"


        val thisStudentCourse = connections.value?.find {
            x -> x.student_id == students.value?.get(position)?.id && x.course_id == courseId }

        // Zaznaczenie/odznaczenie checkboxa:
        checkbox.isChecked = thisStudentCourse != null

        // Mapowanie:
        val studentID = students.value!!.get(position).id
        checkedStudents.put(studentID, checkbox.isChecked)

        // Listener korygujący mapę:
        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkedStudents.replace(studentID, true)

            } else {
                checkedStudents.replace(studentID, false)
            }
        }
    }

    override fun getItemCount(): Int = students.value?.size ?: 0
}
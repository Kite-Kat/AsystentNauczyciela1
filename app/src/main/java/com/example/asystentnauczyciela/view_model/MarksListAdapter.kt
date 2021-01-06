package com.example.asystentnauczyciela.view_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.Mark
import com.example.asystentnauczyciela.model.Student

class MarksListAdapter(private val connections: LiveData<List<Mark>>,private val studentID: Int, private val courseID: Int):ListAdapter<Mark, MarksListAdapter.MarkHolder>(MarksListDiff){

    inner class MarkHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.students_mark_one_row,parent, false)
        return MarkHolder(view)
    }

    override fun onBindViewHolder(holder: MarkHolder, position: Int) {
        var textViewMark = holder.itemView.findViewById<TextView>(R.id.textViewStudentMark)
        var textViewNote = holder.itemView.findViewById<TextView>(R.id.textViewStudentMarkNote)

        val buttonDMark = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonDelMark)
        val buttonEMark = holder.itemView.findViewById<AppCompatImageButton>(R.id.imageButtonEditMark)
        val mark = getItem(position)

//        if(connections != null){
//
//        }


    }
}



object MarksListDiff: DiffUtil.ItemCallback<Mark>(){
    override fun areItemsTheSame(oldItem: Mark, newItem: Mark)= oldItem == newItem

    override fun areContentsTheSame(oldItem: Mark, newItem: Mark)= oldItem == newItem
}
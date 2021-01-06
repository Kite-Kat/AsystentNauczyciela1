package com.example.asystentnauczyciela.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.*
import com.example.asystentnauczyciela.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        database = Room.databaseBuilder(applicationContext,SCDatabase::class.java, "AsystentNauczyciela").build()
//        repositoryCourse = CourseRepository(database.courseDao())
    }
//
//    companion object {
//        lateinit var database: SCDatabase
//        lateinit var repositoryCourse: CourseRepository
//    }
}
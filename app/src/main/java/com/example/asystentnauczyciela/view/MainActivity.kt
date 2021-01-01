package com.example.asystentnauczyciela.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.asystentnauczyciela.R
import com.example.asystentnauczyciela.model.CourseDao
import com.example.asystentnauczyciela.model.CourseDao_Impl
import com.example.asystentnauczyciela.model.SCDatabase
import com.example.asystentnauczyciela.model.repositories.CourseRepository

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
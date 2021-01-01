package com.example.asystentnauczyciela.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class, Course::class, StudentCourse::class, Mark::class],version=3, exportSchema = true)
abstract class SCDatabase:RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun studentCourseDao(): StudentCourseDao
    abstract fun markDao(): MarkDao

    companion object{
        @Volatile
        private var INSTANCE: SCDatabase?=null

        fun getDatabase(context: Context):SCDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null)
                return tempInstance
            else {
                synchronized(this)
                {
                    val instance = Room.databaseBuilder(
                            context.applicationContext,
                            SCDatabase::class.java,
                            "sc_database"
                    ).fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}
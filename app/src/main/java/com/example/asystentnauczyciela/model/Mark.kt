package com.example.asystentnauczyciela.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "mark_table",
        foreignKeys = [
            ForeignKey(
                    entity = Student::class,
                    parentColumns = ["id"],
                    childColumns = ["student_id"],
                    onDelete = ForeignKey.CASCADE
            ),
            ForeignKey(
                    entity = Course::class,
                    parentColumns = ["id"],
                    childColumns = ["course_id"],
                    onDelete = ForeignKey.CASCADE
            )
        ])
data class Mark(@PrimaryKey(autoGenerate = true) val id: Int,
                val student_id:Int,
                val course_id:Int,
                var mark:Double,
                var date: String,
                var note: String)

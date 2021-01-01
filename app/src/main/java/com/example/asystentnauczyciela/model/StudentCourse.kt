package com.example.asystentnauczyciela.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey



@Entity(tableName="student_course_table",
        foreignKeys = [
            ForeignKey(
               entity = Student::class,
               parentColumns = ["id"],
               childColumns = ["student_id"],
               onDelete = CASCADE
            ),
            ForeignKey(
                    entity = Course::class,
                    parentColumns = ["id"],
                    childColumns = ["course_id"],
                    onDelete = CASCADE
            )
        ]
)
data class StudentCourse(@PrimaryKey(autoGenerate = true) val id: Int, val student_id:Int, val course_id:Int)

package com.menesdurak.simpletodo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date

@Entity(tableName = "toDoS")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val note: String,
    val priority: Priority = Priority.LOW,
    val date: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
) : Serializable

enum class Priority {
    LOW,
    MEDIUM,
    HIGH
}

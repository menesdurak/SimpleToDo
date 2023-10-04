package com.menesdurak.simpletodo.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.menesdurak.simpletodo.data.local.entity.ToDo

@Dao
interface ToDoDao {

    @Insert
    suspend fun insertToDo(toDo: ToDo)

    @Update
    suspend fun updateToDo(toDo: ToDo)

    @Delete
    suspend fun deleteToDo(toDo: ToDo)

    @Query("SELECT * FROM toDoS")
    suspend fun getAllToDos(): List<ToDo>

    @Query("SELECT * FROM toDoS WHERE name like '%' || :word || '%' OR note like '%' || :word || '%'")
    suspend fun searchToDos(word: String): List<ToDo>
}
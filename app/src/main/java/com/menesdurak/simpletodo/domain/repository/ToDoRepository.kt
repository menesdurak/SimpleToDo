package com.menesdurak.simpletodo.domain.repository

import com.menesdurak.simpletodo.data.Response
import com.menesdurak.simpletodo.data.local.entity.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun insertToDo(toDo: ToDo)
    suspend fun updateToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)
    suspend fun getAllToDos(): Flow<Response<List<ToDo>>>
    suspend fun searchToDos(word: String): Flow<Response<List<ToDo>>>
}
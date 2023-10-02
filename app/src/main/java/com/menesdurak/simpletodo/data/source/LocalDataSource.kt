package com.menesdurak.simpletodo.data.source

import com.menesdurak.simpletodo.data.Response
import com.menesdurak.simpletodo.data.local.entity.ToDo

interface LocalDataSource {

    suspend fun insertToDo(toDo: ToDo)
    suspend fun updateToDo(toDo: ToDo)
    suspend fun deleteToDo(toDo: ToDo)
    suspend fun getAllToDos(): Response<List<ToDo>>
}
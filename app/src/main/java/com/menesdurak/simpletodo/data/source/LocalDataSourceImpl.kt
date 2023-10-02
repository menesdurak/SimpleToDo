package com.menesdurak.simpletodo.data.source

import com.menesdurak.simpletodo.data.Response
import com.menesdurak.simpletodo.data.local.dao.ToDoDao
import com.menesdurak.simpletodo.data.local.entity.ToDo
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val toDoDao: ToDoDao) : LocalDataSource {
    override suspend fun insertToDo(toDo: ToDo) {
        toDoDao.insertToDo(toDo)
    }

    override suspend fun updateToDo(toDo: ToDo) {
        toDoDao.updateToDo(toDo)
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        val newToDo = ToDo(id = toDo.id, name = "", note = "")
        toDoDao.deleteToDo(newToDo)
    }

    override suspend fun getAllToDos(): Response<List<ToDo>> =
        try {
            val response = toDoDao.getAllToDos()
            Response.Success(response)
        } catch (e: Exception) {
            Response.Error(e)
        }

}
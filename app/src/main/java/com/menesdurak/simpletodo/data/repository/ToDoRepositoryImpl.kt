package com.menesdurak.simpletodo.data.repository

import android.util.Log
import com.menesdurak.simpletodo.data.Response
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.data.source.LocalDataSource
import com.menesdurak.simpletodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(private val localDataSource: LocalDataSource) :
    ToDoRepository {
    override suspend fun insertToDo(toDo: ToDo) {
        localDataSource.insertToDo(toDo)
    }

    override suspend fun updateToDo(toDo: ToDo) {
        localDataSource.updateToDo(toDo)
    }

    override suspend fun deleteToDo(toDo: ToDo) {
        localDataSource.deleteToDo(toDo)
    }

    override suspend fun getAllToDos(): Flow<Response<List<ToDo>>> {
        return flow {
            emit(Response.Loading)
            when (val response = localDataSource.getAllToDos()) {
                is Response.Success -> {
                    emit(Response.Success(response.result))
                }

                is Response.Error -> {
                    emit(Response.Error(response.exception))
                }

                else -> {
                    Log.e("Error", "Ne yaptin dostum?")
                }
            }
        }
    }
}
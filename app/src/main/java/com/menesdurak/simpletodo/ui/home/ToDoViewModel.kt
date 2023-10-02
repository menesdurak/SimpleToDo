package com.menesdurak.simpletodo.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.menesdurak.simpletodo.data.Response
import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.domain.usecase.DeleteToDoUseCase
import com.menesdurak.simpletodo.domain.usecase.GetAllToDosUseCase
import com.menesdurak.simpletodo.domain.usecase.InsertToDoUseCase
import com.menesdurak.simpletodo.domain.usecase.UpdateToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val getAllToDosUseCase: GetAllToDosUseCase,
    private val insertToDoUseCase: InsertToDoUseCase,
    private val updateToDoUseCase: UpdateToDoUseCase,
    private val deleteToDoUseCase: DeleteToDoUseCase,
) : ViewModel() {

    private val _homeUiState = MutableLiveData<HomeUiState>()
    val homeUiState :LiveData<HomeUiState> = _homeUiState

    fun getAllToDos() {
        viewModelScope.launch {
            getAllToDosUseCase().collectLatest {
                when (it) {
                    Response.Loading -> {
                        _homeUiState.postValue(HomeUiState.Loading)
                    }

                    is Response.Success -> {
                        _homeUiState.postValue(HomeUiState.Success(it.result!!))
                    }

                    is Response.Error -> {
                        _homeUiState.postValue(HomeUiState.Error(it.exception))
                    }
                }
            }
        }
    }

    fun insertToDo(toDo: ToDo) {
        viewModelScope.launch {
            insertToDoUseCase(toDo)
        }
    }

    fun updateToDo(toDo: ToDo) {
        viewModelScope.launch {
            updateToDoUseCase(toDo)
        }
    }

    fun deleteToDo(toDo: ToDo) {
        viewModelScope.launch {
            deleteToDoUseCase(toDo)
        }
    }
}
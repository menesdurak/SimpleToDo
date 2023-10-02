package com.menesdurak.simpletodo.ui.home

import com.menesdurak.simpletodo.data.local.entity.ToDo

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<ToDo>) : HomeUiState()
    data class Error(val exception: Exception) : HomeUiState()

}
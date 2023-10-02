package com.menesdurak.simpletodo.domain.usecase

import com.menesdurak.simpletodo.domain.repository.ToDoRepository
import javax.inject.Inject

class GetAllToDosUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {

    suspend operator fun invoke() = toDoRepository.getAllToDos()
}
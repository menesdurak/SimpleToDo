package com.menesdurak.simpletodo.domain.usecase

import com.menesdurak.simpletodo.data.local.entity.ToDo
import com.menesdurak.simpletodo.domain.repository.ToDoRepository
import javax.inject.Inject

class InsertToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {

    suspend operator fun invoke(toDo: ToDo) = toDoRepository.insertToDo(toDo)
}
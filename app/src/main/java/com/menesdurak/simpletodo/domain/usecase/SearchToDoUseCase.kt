package com.menesdurak.simpletodo.domain.usecase

import com.menesdurak.simpletodo.domain.repository.ToDoRepository
import javax.inject.Inject

class SearchToDoUseCase @Inject constructor(private val toDoRepository: ToDoRepository) {

    suspend operator fun invoke(word: String) = toDoRepository.searchToDos(word)
}
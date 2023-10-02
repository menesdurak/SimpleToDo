package com.menesdurak.simpletodo.di.repository

import com.menesdurak.simpletodo.data.repository.ToDoRepositoryImpl
import com.menesdurak.simpletodo.domain.repository.ToDoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ToDoRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindToDoRepository(
        toDoRepositoryImpl: ToDoRepositoryImpl,
    ): ToDoRepository
}
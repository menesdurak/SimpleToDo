package com.menesdurak.simpletodo.di.room

import android.content.Context
import com.menesdurak.simpletodo.data.local.dao.ToDoDao
import com.menesdurak.simpletodo.data.local.db.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return ToDoDatabase.getToDoDatabase(context)
    }

    @Provides
    @Singleton
    fun provideToDoDao(toDoDatabase: ToDoDatabase): ToDoDao {
        return toDoDatabase.getToDoDao()
    }

}
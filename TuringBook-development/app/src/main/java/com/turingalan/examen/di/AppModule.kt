package com.turingalan.examen.di


import com.turingalan.examen.data.repository.BookInMemoryRepository
import com.turingalan.examen.data.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindBookRepository(repository: BookInMemoryRepository): BookRepository
}
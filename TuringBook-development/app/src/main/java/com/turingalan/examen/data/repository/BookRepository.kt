package com.turingalan.examen.data.repository

import com.turingalan.examen.data.model.Book

interface BookRepository {

    suspend fun searchBooks(search:String): List<Book>
    suspend fun readOne(id:Long):Book?
    suspend fun readAll():List<Book>

    suspend fun readFiltered(): List<Book>
}
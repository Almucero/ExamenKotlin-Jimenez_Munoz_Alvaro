package com.turingalan.examen.data.repository

import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BookInMemoryRepository
    @Inject constructor(): BookRepository {
    private val _books: MutableList<Book> = mutableListOf(
        Book(
            id = 10001,
            title = "American Psycho",
            author = "Bret Easton Ellis",
            isbn = "9788495501479",
            editorial = "Debolsillo",
            publicationYear = 1991,
            editionYear = 2001
        ),
        Book(
            id = 10002,
            title = "Moby Dick",
            author = "Herman Melville",
            isbn = "9788408137221",
            editorial = "Austral",
            publicationYear = 1851,
            editionYear = 2015,
        ),
        Book(
            id = 10003,
            title = "La Comunidad del Anillo",
            author = "John Ronald Reuel Tolkien",
            isbn = "9788445017319",
            editorial = "Minotauro",
            publicationYear = 1954,
            editionYear = 2025,
        ),
        Book(
            id = 10004,
            title = "Las Dos Torres",
            author = "John Ronald Reuel Tolkien",
            isbn = "9788445019719",
            editorial = "Minotauro",
            publicationYear = 1954,
            editionYear = 2025,
        ),
        Book(
            id = 10005,
            title = "El retorno del Rey",
            author = "John Ronald Reuel Tolkien",
            isbn = "9788445019818",
            editorial = "Minotauro",
            publicationYear = 1955,
            editionYear = 2025,
        ),
        Book(
            id = 10006,
            title = "Drácula",
            author = "Bram Stoker",
            isbn = "9788408167891",
            editorial = "Austral",
            publicationYear = 1897,
            editionYear = 2017,
        ),
        Book(
            id = 10007,
            title = "¿Sueñan los Androides con Ovejas Eléctricas?",
            author = "Philip K. Dick",
            isbn = "9788445005125",
            editorial = "Austral",
            publicationYear = 1968,
            editionYear = 2019,
        ),
        Book(
            id = 10008,
            title = "Sentido y Sensibilidad",
            author = "Jane Austen",
            isbn = "9788408278924",
            editorial = "Austral",
            publicationYear = 1811,
            editionYear = 2023,
        ),
        Book(
            id = 10009,
            title = "La Dama del Perrito",
            author = "Anton Pavlovich Chejov",
            isbn = "9789500398503",
            editorial = "Austral",
            publicationYear = 1899,
            editionYear = 2011,
        ),
        Book(
            id = 10010,
            title = "El Guardian entre el Centeno",
            author = "J. D. Salinger",
            isbn = " 9788420634098",
            editorial = "Alianza Editiorial",
            publicationYear = 1951,
            editionYear = 1988,
        ),
    )

    //    private val _books: MutableList<Book> = mutableListOf(
    /** Debe buscar por autor, isbn, editorial y/o título
     * @param search Cadena de busqueda
     * @return Lista de libros que cumplen la cadena de busqueda
     */
    val filteredBooks: MutableList<Book> = mutableListOf()
    override suspend fun searchBooks(search: String): List<Book> {
        delay(2000L)
        val allBooks = readAll();
        for (book in allBooks){
            if (
                book.title.contains(search) ||
                book.author.contains(search) ||
                book.isbn.contains(search) ||
                book.editorial.contains(search)
                ) {
                filteredBooks.add(book)
            }
        }
        return filteredBooks
    }

    override suspend fun readFiltered(): List<Book> {
        delay(2000L)
        return filteredBooks
    }

    override suspend fun readOne(id: Long) = _books.firstOrNull { book -> book.id == id }

    override suspend fun readAll() = _books.toList()
}

/*package com.turingalan.examen.data.repository

import com.turingalan.examen.data.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookInMemoryRepository
    @Inject constructor(): BookRepository {
    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(
        listOf(
            Book(
                id = 10001,
                title = "American Psycho",
                author = "Bret Easton Ellis",
                isbn = "9788495501479",
                editorial = "Debolsillo",
                publicationYear = 1991,
                editionYear = 2001
            ),
            Book(
                id = 10002,
                title = "Moby Dick",
                author = "Herman Melville",
                isbn = "9788408137221",
                editorial = "Austral",
                publicationYear = 1851,
                editionYear = 2015,
            ),
            Book(
                id = 10003,
                title = "La Comunidad del Anillo",
                author = "John Ronald Reuel Tolkien",
                isbn = "9788445017319",
                editorial = "Minotauro",
                publicationYear = 1954,
                editionYear = 2025,
            ),
            Book(
                id = 10004,
                title = "Las Dos Torres",
                author = "John Ronald Reuel Tolkien",
                isbn = "9788445019719",
                editorial = "Minotauro",
                publicationYear = 1954,
                editionYear = 2025,
            ),
            Book(
                id = 10005,
                title = "El retorno del Rey",
                author = "John Ronald Reuel Tolkien",
                isbn = "9788445019818",
                editorial = "Minotauro",
                publicationYear = 1955,
                editionYear = 2025,
            ),
            Book(
                id = 10006,
                title = "Drácula",
                author = "Bram Stoker",
                isbn = "9788408167891",
                editorial = "Austral",
                publicationYear = 1897,
                editionYear = 2017,
            ),
            Book(
                id = 10007,
                title = "¿Sueñan los Androides con Ovejas Eléctricas?",
                author = "Philip K. Dick",
                isbn = "9788445005125",
                editorial = "Austral",
                publicationYear = 1968,
                editionYear = 2019,
            ),
            Book(
                id = 10008,
                title = "Sentido y Sensibilidad",
                author = "Jane Austen",
                isbn = "9788408278924",
                editorial = "Austral",
                publicationYear = 1811,
                editionYear = 2023,
            ),
            Book(
                id = 10009,
                title = "La Dama del Perrito",
                author = "Anton Pavlovich Chejov",
                isbn = "9789500398503",
                editorial = "Austral",
                publicationYear = 1899,
                editionYear = 2011,
            ),
            Book(
                id = 10010,
                title = "El Guardian entre el Centeno",
                author = "J. D. Salinger",
                isbn = " 9788420634098",
                editorial = "Alianza Editiorial",
                publicationYear = 1951,
                editionYear = 1988,
            ),
        )
    )

    private val bookList = _books.asStateFlow()

    /** Debe buscar por autor, isbn, editorial y/o título
     * @param search Cadena de busqueda
     * @return Lista de libros que cumplen la cadena de busqueda
     */
    override suspend fun searchBooks(search: String): Flow<List<Book>> {
        delay(2000L)
        val allBooks = _books.value.toMutableList();
        val filteredBooks: Flow<List<Book>> = MutableStateFlow(emptyList())
        for (book in allBooks){
            if (
                book.title.contains(search) ||
                book.author.contains(search) ||
                book.isbn.contains(search) ||
                book.editorial.contains(search)
                ) {
                filteredBooks
            }
        }
        return filteredBooks
    }

    override suspend fun readOne(id: Long): Flow<Book?> {
        return bookList.map { books ->
            books.find { it.id == id }
        }
    }

    override suspend fun readAll(): Flow<List<Book>> {
        return this.bookList
    }
}*/
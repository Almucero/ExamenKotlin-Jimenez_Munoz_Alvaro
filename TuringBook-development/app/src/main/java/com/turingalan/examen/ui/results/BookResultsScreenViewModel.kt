package com.turingalan.examen.ui.results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookResultsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BookRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState.Initial)
    val uiState: StateFlow<ListUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _uiState.value = ListUiState.Loading
                val books = repository.readFiltered()
                _uiState.value = ListUiState.Success(books.asListUiState())
            }
            catch (e: Exception) {
                _uiState.value = ListUiState.Error("No hay resultados")
            }
        }
    }
}

sealed class ListUiState {
    object Initial: ListUiState()
    object Loading: ListUiState()
    data class Error(val message: String): ListUiState()
    data class Success(val books: List<ListItemUiState>): ListUiState()
}

data class ListItemUiState(
    val id: Long,
    val title: String,
    val author: String,
    val editorial: String
)

fun Book.asListItemUiState(): ListItemUiState {
    return ListItemUiState(
        this.id,
        this.title,
        this.author,
        this.editorial
    )
}

fun List<Book>.asListUiState(): List<ListItemUiState> = this.map(Book::asListItemUiState)
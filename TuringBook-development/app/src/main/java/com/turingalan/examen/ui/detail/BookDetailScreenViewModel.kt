package com.turingalan.examen.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.turingalan.examen.data.model.Book
import com.turingalan.examen.data.repository.BookRepository
import com.turingalan.examen.ui.Destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BookRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val route = savedStateHandle.toRoute<Destinations.Details>()
            val book = repository.readOne(route.id)
            book?.let {
                _uiState.value = book.toDetailUiState()
            }
        }
    }
}

fun Book.toDetailUiState(): DetailUiState {
    return DetailUiState(
        this.title,
        this.author,
        this.editorial,
        this.isbn,
        this.publicationYear,
        this.editionYear
    )
}

data class DetailUiState(
    val title: String = "",
    val author: String = "",
    val editorial: String = "",
    val isbn: String = "",
    val publicationYear: Int = 0,
    val editionYear: Int = 0
)
package com.turingalan.examen.ui.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turingalan.examen.data.repository.BookRepository
import com.turingalan.examen.ui.results.ListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BookRepository
): ViewModel() {
    private val _search = MutableStateFlow("")
    val search: StateFlow<String> = _search.asStateFlow()
    fun updateSearch(newSearch: String) {
        _search.value = newSearch
    }

    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = SearchUiState.Done
            repository.searchBooks(_search.value)
        }
    }
}

sealed class SearchUiState {
    object Initial: SearchUiState()
    object Done: SearchUiState()
}
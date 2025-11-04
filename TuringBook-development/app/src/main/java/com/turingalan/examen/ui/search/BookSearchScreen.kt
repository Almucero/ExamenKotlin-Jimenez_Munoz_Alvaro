package com.turingalan.examen.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


// TODO Implementar Composable(s) con pantalla de busqueda
@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier,
    viewModel: BookSearchViewModel = hiltViewModel(),
    search: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val search by viewModel.search.collectAsStateWithLifecycle()

    when (uiState) {
        is SearchUiState.Initial -> {
            //aqui no ocurre nada
        }
        is SearchUiState.Done -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
            ) {
                Column {
                    Text("Turing BookStore")
                    OutlinedTextField(
                        value = search,
                        onValueChange = {
                            viewModel.updateSearch(it)
                        },
                        placeholder = { Text("Las crónicas de Narnia") },
                        singleLine = true,
                        supportingText = { Text("Introduce tu búsqueda") }
                    )
                    Row {
                        Button(
                            onClick = {
                                search()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Buscar")
                        }
                    }
                }
            }
        }
    }
}

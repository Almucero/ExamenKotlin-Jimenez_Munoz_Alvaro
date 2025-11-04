package com.turingalan.examen.ui.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookResultsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookResultsScreenViewModel = hiltViewModel(),
    onNavigateToDetail: (Long) -> Unit,
    volverABusqueda: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        ListUiState.Initial -> {

        }
        ListUiState.Loading -> {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
        is ListUiState.Success -> {
            Text("No hay resultados")
            LazyColumn(
                modifier = modifier,

            ) {
                items((uiState as ListUiState.Success).books, key = { it.id }) { book ->
                    BookResultsItemScreen(
                        id = book.id,
                        title = book.title,
                        author = book.author,
                        editorial = book.editorial,
                        onClickItem = onNavigateToDetail
                    )
                }
            }
            Row(
                modifier = modifier.fillMaxWidth().padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = volverABusqueda,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Volver a la búsqueda")
                }
            }
        }
        is ListUiState.Error -> {
            Column {
                Text("No hay resultados")
                Row(
                    modifier = Modifier.fillMaxWidth().padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = volverABusqueda,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Volver a la búsqueda")
                    }
                }
            }
        }
    }
}


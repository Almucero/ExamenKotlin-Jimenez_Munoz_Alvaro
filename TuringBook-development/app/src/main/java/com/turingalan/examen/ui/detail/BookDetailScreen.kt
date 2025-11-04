package com.turingalan.examen.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun BookDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    onCancel: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Surface(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box() {
                Column {
                    Text(uiState.title)
                    Text(uiState.author)
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box() {
                    Column {
                        Text(uiState.editorial)
                        Text(uiState.isbn)
                    }
                }
                Box() {
                    Column {
                        Text(uiState.publicationYear.toString())
                        Text(uiState.editionYear.toString())
                    }
                }
            }
            Row {
                Button(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Volver a resultados")
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun DetailScreenPreview() {
//    DetailScreen() {}
//}
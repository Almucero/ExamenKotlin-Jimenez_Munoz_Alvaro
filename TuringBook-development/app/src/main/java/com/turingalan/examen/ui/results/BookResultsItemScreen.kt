package com.turingalan.examen.ui.results

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BookResultsItemScreen(
    id: Long,
    title: String,
    author: String,
    editorial: String,
    onClickItem: (Long) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(true, onClick = {
            onClickItem(id)
        }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box() {
            Column() {
                Text(title)
                Text(author)
                Text(editorial)
            }
        }
    }
}
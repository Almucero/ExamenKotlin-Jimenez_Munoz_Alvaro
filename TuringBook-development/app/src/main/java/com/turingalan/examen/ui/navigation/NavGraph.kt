package com.turingalan.examen.ui.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.turingalan.examen.ui.Destinations
import com.turingalan.examen.ui.detail.BookDetailsScreen
import com.turingalan.examen.ui.results.BookResultsScreen
import com.turingalan.examen.ui.search.BookSearchScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.Search
        ) {
            val hostModifier = Modifier.consumeWindowInsets(innerPadding).padding(innerPadding).padding(vertical = 8.dp, horizontal = 10.dp)
            composable<Destinations.Search> {
                BookSearchScreen(
                    modifier = hostModifier,
                    search = {
                        navController.navigate(Destinations.Results)
                    }
                )
            }
            composable<Destinations.Results> {
                BookResultsScreen(
                    modifier = hostModifier,
                    onNavigateToDetail = { id ->
                        navController.navigate(Destinations.Details(id))
                    },
                    volverABusqueda = {
                        navController.navigate(Destinations.Search)
                    }
                )
            }
            composable<Destinations.Details> {
                BookDetailsScreen(
                    modifier = hostModifier,
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}


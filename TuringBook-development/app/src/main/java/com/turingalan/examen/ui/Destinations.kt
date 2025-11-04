package com.turingalan.examen.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations(val route: String) {
    @Serializable
    object Search: Destinations("searchScreen")
    @Serializable
    object Results: Destinations("resultsScreen")
    @Serializable
    data class Details(val id: Long): Destinations("detailScreen/$id")
}
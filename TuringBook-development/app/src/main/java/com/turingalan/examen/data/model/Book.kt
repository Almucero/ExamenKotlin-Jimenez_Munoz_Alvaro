package com.turingalan.examen.data.model

data class Book(
    val id:Long,
    val title:String,
    val author:String,
    val isbn:String,
    val editorial:String,
    val editionYear:Int,
    val publicationYear:Int,
)
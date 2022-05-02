package br.com.bruno.entities

data class Film(
    val id: Int,
    val title: String,
    val genre: String,
    val duration: String,
    val synopsis: String,
    val watched: Boolean,
)

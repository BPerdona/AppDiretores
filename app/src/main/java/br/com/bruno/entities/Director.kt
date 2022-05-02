package br.com.bruno.entities

data class Director(
    val name: String,
    val age: Int,
    val films: ArrayList<Film>,
)

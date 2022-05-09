package br.com.bruno.entities

import androidx.annotation.DrawableRes

data class Director(
    val id: Int,
    val name: String,
    val age: Int,
    var films: List<Film>,
    @DrawableRes val image: Int
)

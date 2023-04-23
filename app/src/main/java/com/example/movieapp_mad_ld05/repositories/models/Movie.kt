package com.example.movieapp_mad_ld05.repositories.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie")

data class Movie(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var year: String = "",
    var genre: List<Genre> = listOf(),
    var director: String = "",
    var actors: String = "",
    var plot: String = "No plot available",
    var images: List<String> = listOf(),
    var rating: Double = 0.0,
    var isFavorite: Boolean = false
)
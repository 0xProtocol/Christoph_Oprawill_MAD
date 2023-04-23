package com.example.movieapp_mad_ld05.utils

import androidx.room.TypeConverter
import com.example.movieapp_mad_ld05.repositories.models.Genre

class CustomConverters {

    @TypeConverter
    fun listToString(value: List<String>) = value.joinToString { "," }

    @TypeConverter
    fun stringToList(value: String) = value.split(",").map { it.trim() }

    @TypeConverter
    fun genreListToString(value: List<Genre>): String =
        value.joinToString(separator = ",") { it.toString() }

    @TypeConverter
    fun stringToGenreList(value: String): List<Genre> = value.split(",").map { Genre.valueOf(it) }

}
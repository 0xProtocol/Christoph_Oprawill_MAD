package com.example.movieapp_mad_ld05

import com.example.movieapp_mad_ld05.repositories.models.Genre

object Validator {
    fun validateMovieTitle(title: String): ValidationResult {
        return ValidationResult(title.isNotBlank())
    }

    fun validateMovieYear(year: String): ValidationResult {
        return ValidationResult(year.isNotBlank())
    }

    fun validateMovieGenres(genres: List<Genre>): ValidationResult {
        return ValidationResult(genres.isNotEmpty())
    }

    fun validateMovieDirector(director: String): ValidationResult {
        return ValidationResult(director.isNotBlank())
    }

    fun validateMovieActors(actors: String): ValidationResult {
        return ValidationResult(actors.isNotBlank())
    }

    fun validateMovieRating(rating: String): ValidationResult {
        return ValidationResult(rating.isNotBlank())
    }
}

data class ValidationResult(
    val successful: Boolean
)

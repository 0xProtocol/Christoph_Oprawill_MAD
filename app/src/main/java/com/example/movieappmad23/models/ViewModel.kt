package com.example.movieappmad23.models

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    var movies = getMovies().toMutableStateList()
    val allMovies: List<Movie>
        get() = movies

    var moviesfavourite = mutableListOf<Movie>().toMutableStateList()
    val favoriteMovies: List<Movie>
        get() = moviesfavourite

    var addMovie: Movie = Movie("", "", "", listOf(), "", "", "", listOf(), 0.0f)

    var isDisabled: MutableState<Boolean> = mutableStateOf(false)

    var title = mutableStateOf(addMovie.title)
    var errorTitle: MutableState<Boolean> = mutableStateOf(false)

    val year = mutableStateOf(addMovie.year)
    var errorYear: MutableState<Boolean> = mutableStateOf(false)

    var director = mutableStateOf(addMovie.director)
    var errorDirector: MutableState<Boolean> = mutableStateOf(false)

    var actors = mutableStateOf(addMovie.actors)
    var errorActors: MutableState<Boolean> = mutableStateOf(false)

    var plot = mutableStateOf(addMovie.plot)
    var errorPlot: MutableState<Boolean> = mutableStateOf(false)

    var rating = mutableStateOf(addMovie.rating.toString().replace("0.0", ""))
    var errorRating: MutableState<Boolean> = mutableStateOf(false)

    var genreItems = mutableStateOf(
        Genre.values().map { genre ->
            ListItemSelectable(
                title = genre.toString(),
                isSelected = false
            )
        }
    )
    var genreError: MutableState<Boolean> = mutableStateOf(false)

    fun init() {
        validateTitle()
        validateYear()
        validateDirector()
        validateActors()
        validatePlot()
        validateRating()
        validateGenres()
    }

    fun addMovie(
        title: String,
        year: String,
        director: String,
        genres: List<Genre>,
        actors: String,
        plot: String,
        rating: String
    ) {
        val newMovie = Movie(
            id = "$title + $year + $director+ $genres + $actors",
            title = title,
            year = year,
            director = director,
            genre = genres,
            actors = actors,
            plot = plot,
            rating = rating.toFloat(), images = listOf(
                "https://i.ds.at/rc9FAA/rs:fill:750:0/plain/2014/01/14/1388693526907-wallstreet-800.jpg",
        "https://www.apple.com/newsroom/images/product/app-store/app-store-awards/Apple_App-Store-Awards-2021_hero_12022021_big.jpg.large.jpg",)
        )
        movies.add(newMovie)

    }

    fun toggleFavorite(movie: Movie) {
        movies.find { it.id == movie.id }?.let { task ->
            task.isFavorite = !task.isFavorite
            if (task.isFavorite) {
                moviesfavourite.add(movie)
            } else {
                moviesfavourite.remove(movie)
            }
        }
    }
    fun validateTitle() {
        errorTitle.value = title.value.isEmpty()
        Enable()
    }

    fun validateYear() {
        errorYear.value = year.value.isEmpty()
        Enable()
    }

    fun validateDirector() {
        errorDirector.value = director.value.isEmpty()
        Enable()
    }

    fun validateActors() {
        errorActors.value = actors.value.isEmpty()
        Enable()
    }

    fun validatePlot() {
        errorPlot.value = plot.value.isEmpty()
        Enable()
    }

    fun validateRating() {
        try {
            rating.value.toFloat()
            errorRating.value = false
        } catch (e: java.lang.Exception) {
            errorRating.value = true
        } finally {
            Enable()
        }
    }

    fun validateGenres() {
        genreError.value = true
        genreItems.value.forEach genres@{
            if (it.isSelected) {
                genreError.value = false
                return@genres
            }
        }
        Enable()
    }

    private fun Enable() {
        isDisabled.value =
            (errorTitle.value.not()
                    && errorYear.value.not()
                    && errorDirector.value.not()
                    && errorActors.value.not()
                    && errorPlot.value.not()
                    && errorRating.value.not()
                    && genreError.value.not()
                    )
    }
}
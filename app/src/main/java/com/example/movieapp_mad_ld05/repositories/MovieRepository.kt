package com.example.movieapp_mad_ld05.repositories

import com.example.movieapp_mad_ld05.data.MovieDao
import com.example.movieapp_mad_ld05.repositories.models.Movie
import kotlinx.coroutines.flow.Flow

class MovieRepository(private val movieDao: MovieDao) {

    suspend fun addMovie(movie: Movie) = movieDao.add(movie)
    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)
    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAll()
    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavorites()
}
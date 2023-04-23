package com.example.movieapp_mad_ld05.utils

import android.content.Context
import com.example.movieapp_mad_ld05.data.MovieDatabase
import com.example.movieapp_mad_ld05.repositories.MovieRepository
import com.example.movieapp_mad_ld05.viewmodels.factory.AddScreenViewModelFactory
import com.example.movieapp_mad_ld05.viewmodels.factory.FavoritesViewModelFactory
import com.example.movieapp_mad_ld05.viewmodels.factory.MoviesViewModelFactory

object InjectorUtils {

    /*fun provideTaskViewModelFactory(context: Context): TaskViewModelFactory {
        val repository = getTaskRepository(context)
        return TaskViewModelFactory(repository)
    }*/

    fun getMovieRepository(context: Context): MovieRepository {
        return MovieRepository(MovieDatabase.getDatabase(context).movieDao())
    }

    fun provideAddMovieScreenViewModelFactory(context: Context): AddScreenViewModelFactory {
        val repository = getMovieRepository(context)
        return AddScreenViewModelFactory(repository = repository)
    }

    fun provideMovieViewModelFactory(context: Context): MoviesViewModelFactory {
        val repository = getMovieRepository(context)
        return MoviesViewModelFactory(repository = repository)

    }

    fun provideFavoriteViewModelFactory(context: Context): FavoritesViewModelFactory {
        val repository = getMovieRepository(context)
        return FavoritesViewModelFactory(repository = repository)
    }


}
package com.example.movieapp_mad_ld05.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp_mad_ld05.repositories.MovieRepository
import com.example.movieapp_mad_ld05.viewmodels.AddScreenViewModel

class AddScreenViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddScreenViewModel::class.java)) {
            return AddScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Error")
    }
}
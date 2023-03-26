package com.example.movieapp_mad.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp_mad.models.Movie
import com.example.movieapp_mad.models.getMovies
import com.example.movieapp_mad.others.ImageRow
import com.example.movieapp_mad.others.MovieRow
import com.example.movieapp_mad.others.SimpleAppBar

@Composable
fun DetailScreen(navController: NavController, movieId: String?){
    val movie: Movie = getMovies()[getMovies().indexOfFirst { it.id == movieId }]
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
        ) {
            SimpleAppBar(title = movie.title, navController = navController)
            MovieRow(movie = movie){}
            ImageRow(images = movie.images, title = "Img" )
        }
    }
}
package com.example.movieapp_mad.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieapp_mad.models.Movie
import com.example.movieapp_mad.models.getMovies
import com.example.movieapp_mad.others.MovieRow
import com.example.movieapp_mad.others.SimpleAppBar

@Composable
fun FavoritesScreen(navController: NavController){
    val favoriteMovies: List<Movie> = listOf(getMovies()[0],getMovies()[1],getMovies()[2])
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(Modifier.fillMaxWidth()) {
            SimpleAppBar(title = "Favorites", navController = navController)
            LazyColumn (userScrollEnabled = true) {
                items(favoriteMovies) { movies ->
                    MovieRow(movies){}
                }
            }
        }
    }
}
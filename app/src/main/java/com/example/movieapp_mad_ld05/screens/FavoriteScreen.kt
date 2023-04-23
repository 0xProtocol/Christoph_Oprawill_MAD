package com.example.movieapp_mad_ld05.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp_mad_ld05.R
import com.example.movieapp_mad_ld05.viewmodels.FavoritesViewModel
import com.example.movieapp_mad_ld05.widgets.MovieRow
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(navController: NavController, favViewModel: FavoritesViewModel) {
    val favoriteMoviesState by favViewModel.favoriteMoviesState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(elevation = 2.dp) {
            Row {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )

                Spacer(modifier = Modifier.width(20.dp))

                stringResource(id = R.string.add_movie)}}
    }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LazyColumn {
                items(favoriteMoviesState) { movie ->
                    MovieRow(
                        movie = movie,
                        onMovieRowClick = { movieId ->
                            navController.navigate(route = Screen.DetailScreen.withId(movieId))
                        },
                        onFavClick = { likedMovie ->
                            coroutineScope.launch {
                                favViewModel.updateFavoriteMovies(likedMovie)
                            }
                        }
                    )
                }
            }
        }
    }
}
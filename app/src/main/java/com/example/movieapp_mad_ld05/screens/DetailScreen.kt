package com.example.movieapp_mad_ld05.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp_mad_ld05.repositories.models.Movie
import com.example.movieapp_mad_ld05.viewmodels.HomeViewModel
import com.example.movieapp_mad_ld05.widgets.MovieRow
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    navController: NavController,
    detailViewModel: HomeViewModel,
    movieId:String?) {
    val coroutineScope = rememberCoroutineScope()
    movieId?.let {
        val movie = detailViewModel.movieListState.value.filter { it.id == movieId  }[0]
        val scaffoldState = rememberScaffoldState()

        Scaffold(scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(elevation = 3.dp) {
                    Row {
                        Icon(imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow back",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            }
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(text = movie.title)
                    }
                }
            },
        ) { padding ->
            MainContent(
                Modifier.padding(padding),
                movie,
                onFavClick = { movie ->
                    coroutineScope.launch {
                        detailViewModel.updateFavoriteMovies(movie)
                    }
                }
            )
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    onFavClick: (Movie) -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            MovieRow(
                movie = movie,
                onFavClick = { movie ->
                    onFavClick(movie)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Text(text = "Movie Images", style = MaterialTheme.typography.h5)
            LazyRow {
                items(movie.images) { image ->
                    Card(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(240.dp),
                        elevation = 4.dp
                    ) {

                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(image)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Movie poster",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}
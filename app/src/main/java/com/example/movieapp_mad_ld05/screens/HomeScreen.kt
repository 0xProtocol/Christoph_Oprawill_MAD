package com.example.movieapp_mad_ld05.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp_mad_ld05.viewmodels.HomeViewModel
import com.example.movieapp_mad_ld05.widgets.MovieRow
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    homeViewModel: HomeViewModel
){
    Scaffold(topBar = {
        var showMenu by remember { mutableStateOf(false) }

        TopAppBar(
            title = { "Home" },
            actions = {
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(onClick = { navController.navigate(Screen.AddMovieScreen.route) }) {
                        Row {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Add Movie", modifier = Modifier.padding(4.dp))
                            Text(text = "Add Movie", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp))
                        }
                    }
                    DropdownMenuItem(onClick = { navController.navigate(Screen.FavoriteScreen.route) }) {
                        Row {
                            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites", modifier = Modifier.padding(4.dp))
                            Text(text = "Favorites", modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp))
                        }
                    }
                }
            }
        )
    }) { padding ->
        MainContent(
            modifier = Modifier.padding(padding),
            navController = navController,
            viewModel = homeViewModel
        )
    }
}

@Composable
fun MainContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: HomeViewModel
) {
    MovieList(
        modifier = modifier,
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel
) {
    val movieListState by viewModel.movieListState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        items(items = movieListState) { movieItem ->
            MovieRow(
                movie = movieItem,
                onMovieRowClick = { movieId ->
                    navController.navigate(Screen.DetailScreen.withId(movieId))
                },
                onFavClick  = { movie ->
                    coroutineScope.launch {
                        viewModel.updateFavoriteMovies(movie)
                    }
                }
            )
        }
    }
}


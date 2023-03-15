package com.example.movieapp_mad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapp_mad.models.Movie
import com.example.movieapp_mad.models.getMovies
import com.example.movieapp_mad.ui.theme.MovieApp_MADTheme
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp_MADTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        TopBar()
                        MovieList(movieList = getMovies())
                    }
                }
            }
        }
    }
}


@Composable
fun MovieItem(
    movie: Movie
) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    var isLiked by remember {
        mutableStateOf(Color.Red)
    }
    var isOpened by remember {
        mutableStateOf(Icons.Rounded.KeyboardArrowDown)
    }

    Card(shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(vertical = 20.dp)) {
        Column {
            Box(modifier = Modifier.height(150.dp)) {
                val painter = rememberAsyncImagePainter(model = movie.images[1])
                Image(
                    painter = painter,
                    contentDescription = "img desc",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription =  "icon desc",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                        .clickable {
                            isLiked = if (isLiked == Color.Red) {
                                Color.White
                            } else {
                                Color.Red
                            }
                        },
                    tint = isLiked,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = movie.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Icon(
                    imageVector = isOpened,
                    contentDescription = "img desc",
                    modifier = Modifier.clickable {
                        if (isVisible) {
                            isOpened = Icons.Rounded.KeyboardArrowDown
                            isVisible = false
                        } else {
                            isOpened = Icons.Rounded.KeyboardArrowUp
                            isVisible = true
                        }
                    }
                )
            }
            AnimatedVisibility(visible = isVisible) {
                Column(Modifier.padding(15.dp)) {
                    Text(text = "Director: ${movie.director}")
                    Text(text = "Release: ${movie.year}")
                    Text(text = "Genre: ${movie.genre}")
                    Text(text = "Actors: ${movie.actors}")
                    Text(text = "Rating: ${movie.rating}" )
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(startIndent = 0.dp, thickness = 2.dp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Plot :")
                    Text(text = movie.plot)
                }

            }
        }
    }
}

//render multiple movie items
@Composable
fun MovieList(movieList: List<Movie>) {
    LazyColumn {
        items(movieList) { movie -> MovieItem(movie = movie) }
    }
}

@Composable
fun TopBar() {
    var isFavoritesCollapsed by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        elevation = 4.dp,
        title = {
            Text("Movies")
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        actions = {
            IconButton(
                onClick = {
                    isFavoritesCollapsed = true
                },
            ) {
                Icon(Icons.Filled.MoreVert, null)
            }
            DropdownMenu(
                expanded = isFavoritesCollapsed,
                onDismissRequest = {
                    isFavoritesCollapsed = false
                }
            ) {
                DropdownMenuItem(onClick = { }) {
                    Row {
                        Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null)
                        Text(text = " Favorites ")
                    }
                }
            }
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieApp_MADTheme {
        TopBar()
        MovieList(movieList = getMovies())
    }
}
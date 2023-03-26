package com.example.movieapp_mad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp_mad.navigation.Navigation
import com.example.movieapp_mad.ui.theme.MovieApp_MADTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp_MADTheme {
                Navigation()
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        TopBar()
                        MovieList(movieList = getMovies())
                    }
                }*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieApp_MADTheme {
        //TopBar()
        //MovieList(movieList = getMovies())
        Navigation()
    }
}
package com.example.movieapp_mad_ld05

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieapp_mad_ld05.navigation.Navigation
import com.example.movieapp_mad_ld05.ui.theme.movieapp_mad_ld05

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            movieapp_mad_ld05 {
                Navigation()
            }
        }
    }
}


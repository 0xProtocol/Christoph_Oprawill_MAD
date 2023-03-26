package com.example.movieapp_mad.others

sealed class Screen(val route: String){
    object Home: Screen(route = "home_screen")
    object Favorites: Screen(route = "favorites_screen")
    object Detail: Screen(route = "detail_screen")
}
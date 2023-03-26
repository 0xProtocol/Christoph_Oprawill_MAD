package com.example.movieapp_mad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp_mad.others.Screen
import com.example.movieapp_mad.screens.HomeScreen
import com.example.movieapp_mad.screens.FavoritesScreen
import com.example.movieapp_mad.screens.DetailScreen

@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(
            route = Screen.Home.route
        ){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Favorites.route
        ){
            FavoritesScreen(navController = navController)
        }
        composable(
            route= Screen.Detail.route +"/{movieId}",
            arguments = listOf(navArgument("movieId"){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }
    }
}
/*
@Composable
fun HomeScreenContent(navController: NavController) {
    val navigateToDetailScreen: (String) -> Unit = { movieId ->
        navController.navigate(Screen.Detail.withArgs("movieId" to movieId))
    }
    HomeScreen(navigateToDetailScreen)
}

@Composable
fun DetailScreenContent(navController: NavController) {
    composable(Screen.Detail.route + "/{movieId}") { backStackEntry ->
        val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
        DetailScreen(navController, movieId)
    }
}

//
@Composable
fun FavoritesScreenContent(navController: NavController) {
    FavoritesScreen(navController)
}*/
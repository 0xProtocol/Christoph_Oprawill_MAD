package com.example.movieapp_mad_ld05.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp_mad_ld05.screens.*
import com.example.movieapp_mad_ld05.utils.InjectorUtils
import com.example.movieapp_mad_ld05.viewmodels.AddScreenViewModel
import com.example.movieapp_mad_ld05.viewmodels.FavoritesViewModel
import com.example.movieapp_mad_ld05.viewmodels.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))

    val favoritesViewModel: FavoritesViewModel = viewModel(factory = InjectorUtils.provideFavoriteViewModelFactory(
        LocalContext.current))

    val addMoviesViewModel: AddScreenViewModel = viewModel(factory = InjectorUtils.provideAddMovieScreenViewModelFactory(
        LocalContext.current))

    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }

        composable(Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController, favViewModel = favoritesViewModel)
        }
        
        composable(Screen.AddMovieScreen.route) {
            AddMovieScreen(navController = navController, addScreenViewModel = addMoviesViewModel)
        }

        composable(Screen.DetailScreen.route, arguments = listOf(navArgument(name = DETAIL_ARGUMENT_KEY) {type = NavType.StringType})) { backStackEntry ->
            DetailScreen(navController = navController,
                detailViewModel = homeViewModel,
                movieId = backStackEntry.arguments?.getString(DETAIL_ARGUMENT_KEY))
        }
    }
}
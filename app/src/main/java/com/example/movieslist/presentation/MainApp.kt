package com.example.movieslist.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movieslist.navigation.Routes
import com.example.movieslist.presentation.moviedetails.MovieDetailsScreen
import com.example.movieslist.presentation.movieslist.MoviesListScreen

@Composable
fun MainApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.MoviesList
        ) {
            composable<Routes.MoviesList> {
                MoviesListScreen(innerPadding) { id ->
                    navController.navigate(Routes.MovieDetails(id = id))
                }
            }

            composable<Routes.MovieDetails> { backStackEntry ->
                val movieDetailsRoute: Routes.MovieDetails = backStackEntry.toRoute()
                MovieDetailsScreen(innerPadding = innerPadding, id = movieDetailsRoute.id)
            }
        }
    }
}
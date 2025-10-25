package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.presentation.detail.CharacterDetailScreen
import com.example.presentation.list.CharacterListScreen


// Defines the navigation graph for the app using Jetpack Compose Navigation.
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "characterList"
    ) {
        // Route for character list screen.
        composable("characterList") {
            CharacterListScreen(
                onCharacterClick = { id ->
                    navController.navigate("characterDetail/$id")
                }
            )
        }
        // Route for character detail screen with ID argument.
        composable(
            route = "characterDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            CharacterDetailScreen(characterId = id, navController = navController)
        }
    }
}

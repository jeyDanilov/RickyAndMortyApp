package com.example.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

// Composable screen that displays detailed info about a character.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    navController: NavHostController
) {
    // Injects the ViewModel using Hilt.
    val viewModel: CharacterDetailViewModel = hiltViewModel()
    // Observes the character state from the ViewModel.
    val character by viewModel.character.collectAsState()

    // Scaffold layout with a top app bar.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Детали персонажа") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        // If character is loaded, show details.
        character?.let {
            Column(modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
            ) {
                // Character image.
                AsyncImage(
                    model = it.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                // Character attributes.
                Text("Имя: ${it.name}", style = MaterialTheme.typography.titleLarge)
                Text("Статус: ${it.status}", style = MaterialTheme.typography.bodyMedium)
                Text("Вид: ${it.species}", style = MaterialTheme.typography.bodyMedium)
                Text("Тип: ${it.type}", style = MaterialTheme.typography.bodyMedium)
                Text("Пол: ${it.gender}", style = MaterialTheme.typography.bodyMedium)
                Text("Локация: ${it.location}", style = MaterialTheme.typography.bodyMedium)
                Text("Эпизодов: ${it.episodeCount}", style = MaterialTheme.typography.bodyMedium)
            }
        } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Loading indicator while character is null.
            CircularProgressIndicator()
        }
    }
}

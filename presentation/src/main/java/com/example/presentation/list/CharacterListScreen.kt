package com.example.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.filter.DropdownFilter
import com.example.presentation.filter.FilterViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


// Composable screen that displays a searchable and filterable list of characters.
@Composable
fun CharacterListScreen(
    onCharacterClick: (Int) -> Unit
) {
    // ViewModel for character list and loading state.
    val viewModel: CharacterListViewModel = hiltViewModel()
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    // ViewModel for filter state.
    val filterViewModel: FilterViewModel = hiltViewModel()
    val selectedStatus by filterViewModel.selectedStatus.collectAsState()
    val selectedGender by filterViewModel.selectedGender.collectAsState()
    val selectedSpecies by filterViewModel.selectedSpecies.collectAsState()

    // Local state for search query.
    var searchQuery by remember { mutableStateOf("") }

    //  Trigger search when query changes.
    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            viewModel.searchCharacters(searchQuery)
        } else {
            viewModel.refresh() // если поле пустое — показываем всех
        }
    }

    // 🎛 Trigger filtering when any filter changes.
    LaunchedEffect(selectedStatus, selectedGender, selectedSpecies) {
        if (selectedStatus != null || selectedGender != null || selectedSpecies != null) {
            viewModel.applyFilters(selectedStatus, selectedGender, selectedSpecies)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // Filter dropdowns.
        DropdownFilter(
            selectedStatus = selectedStatus,
            onStatusChange = filterViewModel::onStatusChange,
            selectedGender = selectedGender,
            onGenderChange = filterViewModel::onGenderChange,
            selectedSpecies = selectedSpecies,
            onSpeciesChange = filterViewModel::onSpeciesChange
        )

        // Search input field.
        androidx.compose.material3.OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Поиск персонажа") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            singleLine = true
        )

        // Swipe-to-refresh wrapper.
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() }
        ) {
            when {
                // Loading state.
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                // Empty result state.
                characters.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Ничего не найдено", color = Color.Gray)
                    }
                }

                // Display character cards in a vertical grid.
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp)
                    ) {
                        items(characters) { character ->
                            CharacterCard(
                                name = character.name,
                                imageUrl = character.image,
                                status = character.status,
                                species = character.species,
                                onClick = { onCharacterClick(character.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

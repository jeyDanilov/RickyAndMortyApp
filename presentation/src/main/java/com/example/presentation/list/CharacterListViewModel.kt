package com.example.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom
import com.example.domain.usecase.FilterCharactersUseCase
import com.example.data.repository.CharacterRepositoryImpl
import com.example.domain.usecase.SearchCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing character list, search, filters, and refresh logic.
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepositoryDom,
    private val filterCharactersUseCase: FilterCharactersUseCase,
    private val searchCharactersUseCase: SearchCharactersUseCase

) : ViewModel() {

    // Holds the current list of characters.
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    // Loading indicator for initial or filtered fetch.
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Refresh indicator for swipe-to-refresh.
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    // Initial load when ViewModel is created.
    init {
        loadCharacters()
    }

    // Public method to trigger manual refresh.
    fun refresh() {
        loadCharacters(refresh = true)
    }

    // Loads characters from local DB, optionally refreshing from API.
    private fun loadCharacters(refresh: Boolean = false) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Refresh from API if needed (only if using CharacterRepositoryImpl).
                if (repository is CharacterRepositoryImpl && (refresh || _characters.value.isEmpty())) {
                    (repository.refreshFromApi())
                }
                // Load from Room (offline cache)
                val list = repository.getCharacters().first()
                _characters.value = list

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    // Searches characters by name using use case.
    fun searchCharacters(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = searchCharactersUseCase(query)
            _characters.value = result
            _isLoading.value = false
        }
    }

    // Applies filters using use case.
    fun applyFilters(status: String?, gender: String?, species: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            val filtered = filterCharactersUseCase(status, gender, species)
            _characters.value = filtered
            _isLoading.value = false
        }
    }
}

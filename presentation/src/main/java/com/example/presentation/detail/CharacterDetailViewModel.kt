package com.example.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Character
import com.example.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the character detail screen, scoped with Hilt for dependency injection.
@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Internal mutable state holding the character.
    private val _character = MutableStateFlow<Character?>(null)
    // Public read-only state exposed to the UI.
    val character: StateFlow<Character?> = _character.asStateFlow()

    // Automatically loads character when ViewModel is created and ID is available.
    init {
        savedStateHandle.get<Int>("id")?.let {
            loadCharacter(it)
        }
    }

    // Launches coroutine to fetch character and update state.
    private fun loadCharacter(id: Int) {
        viewModelScope.launch {
            _character.value = getCharacterUseCase(id)
        }
    }
}

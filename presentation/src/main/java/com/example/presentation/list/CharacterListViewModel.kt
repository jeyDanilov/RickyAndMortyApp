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

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: CharacterRepositoryDom,
    private val filterCharactersUseCase: FilterCharactersUseCase,
    private val searchCharactersUseCase: SearchCharactersUseCase

) : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        loadCharacters()
    }

    fun refresh() {
        loadCharacters(refresh = true)
    }

    private fun loadCharacters(refresh: Boolean = false) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Если первый запуск или юзер обновляет вручную
                if (repository is CharacterRepositoryImpl && (refresh || _characters.value.isEmpty())) {
                    (repository.refreshFromApi())
                }
                // Теперь загружаем из Room (офлайн)
                val list = repository.getCharacters().first()
                    _characters.value = list

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchCharacters(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = searchCharactersUseCase(query)
            _characters.value = result
            _isLoading.value = false
        }
    }

    fun applyFilters(status: String?, gender: String?, species: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            val filtered = filterCharactersUseCase(status, gender, species)
            _characters.value = filtered
            _isLoading.value = false
        }
    }
}

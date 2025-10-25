package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom
import javax.inject.Inject

// Use case for searching characters by name.
class SearchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepositoryDom
) {
    // Enables calling the use case like a function: useCase(query).
    suspend operator fun invoke(query: String): List<Character> {
        return repository.searchCharacters(query)
    }
}
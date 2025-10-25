package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom
import javax.inject.Inject

class SearchCharactersUseCase @Inject constructor(
    private val repository: CharacterRepositoryDom
) {
    suspend operator fun invoke(query: String): List<Character> {
        return repository.searchCharacters(query)
    }
}
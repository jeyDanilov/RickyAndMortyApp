package com.example.domain.usecase


import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom

// Use case for filtering characters based on status, gender, and species.
class FilterCharactersUseCase(
    private val repository: CharacterRepositoryDom
) {
    // Enables calling the use case like a function (e.g. useCase(status, gender, species)).
    suspend operator fun invoke(
        status: String?,
        gender: String?,
        species: String?
    ): List<Character> {

        return repository.getCharactersDirectlyFiltered(status, gender, species)
    }
}
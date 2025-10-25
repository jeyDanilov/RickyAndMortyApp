package com.example.domain.usecase


import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom

class FilterCharactersUseCase(
    private val repository: CharacterRepositoryDom
) {
    suspend operator fun invoke(
        status: String?,
        gender: String?,
        species: String?
    ): List<Character> {

        return repository.getCharactersDirectlyFiltered(status, gender, species)
    }
}
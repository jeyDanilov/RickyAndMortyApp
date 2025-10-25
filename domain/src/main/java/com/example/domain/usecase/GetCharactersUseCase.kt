package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom
import javax.inject.Inject


// Use case for retrieving a single character by ID.
class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepositoryDom
) {
    // Enables calling the use case like a function: useCase(id).
    suspend operator fun invoke(id: Int): Character? {
        return repository.getCharacterById(id)
    }
}
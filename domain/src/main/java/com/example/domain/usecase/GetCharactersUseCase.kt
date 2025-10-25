package com.example.domain.usecase

import com.example.domain.model.Character
import com.example.domain.repository.CharacterRepositoryDom
import javax.inject.Inject


class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepositoryDom
) {
    suspend operator fun invoke(id: Int): Character? {
        return repository.getCharacterById(id)
    }
}
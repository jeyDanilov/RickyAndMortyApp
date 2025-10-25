package com.example.domain.repository

import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepositoryDom {
    fun getCharacters(): Flow<List<Character>>
    suspend fun getCharacterById(id: Int): Character?

    suspend fun searchCharacters(query: String): List<Character>


    suspend fun getCharactersDirectlyFiltered(
        status: String?,
        gender: String?,
        species: String?
    ): List<Character>
}


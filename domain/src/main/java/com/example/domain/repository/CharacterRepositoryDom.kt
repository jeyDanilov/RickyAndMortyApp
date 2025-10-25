package com.example.domain.repository

import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow

// Domain-layer repository interface for accessing character data.
interface CharacterRepositoryDom {

    // Returns all characters as a reactive Flow from the local database.
    fun getCharacters(): Flow<List<Character>>

    // Retrieves a character by its ID.
    suspend fun getCharacterById(id: Int): Character?

    // Searches characters by name (local DB).
    suspend fun searchCharacters(query: String): List<Character>


    // Fetches filtered characters from API or local fallback.
    suspend fun getCharactersDirectlyFiltered(
        status: String?,
        gender: String?,
        species: String?
    ): List<Character>
}


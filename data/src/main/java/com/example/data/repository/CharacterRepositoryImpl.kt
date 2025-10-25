package com.example.data.repository

import android.util.Log
import com.example.domain.model.Character
import com.example.data.local.CharacterDao
import com.example.data.remote.RickAndMortyApi
import com.example.data.remote.mapper.toDomain
import com.example.data.remote.mapper.toEntity
import com.example.domain.repository.CharacterRepositoryDom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Concrete implementation of the domain repository interface using API and local DAO.
class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi,
    private val dao: CharacterDao
) : CharacterRepositoryDom {

    // Returns all characters from local database as a reactive Flow.
    override fun getCharacters(): Flow<List<Character>> =
        dao.getAll().map { list -> list.map { it.toDomain() } }

    // Retrieves a single character by ID from local database.
    override suspend fun getCharacterById(id: Int): Character? =
        dao.getById(id)?.toDomain()

    // Searches characters by name in local database.
    override suspend fun searchCharacters(query: String): List<Character> {
        return try {
            dao.searchByName(query).map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // Fetches filtered characters from API, caches them, or falls back to local DB on failure.
    override suspend fun getCharactersDirectlyFiltered(
        status: String?,
        gender: String?,
        species: String?
    ): List<Character> {
        return try {
            val response = api.getFilteredCharacters(status, gender, species)
            val entities = response.results.map { it.toEntity() }
            dao.clear()
            dao.insertAll(entities)
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            // Возвращаем из Room при ошибке сети
            dao.getFiltered(status, gender, species).map { it.toDomain() }
        }
    }

    // Refreshes local database with latest characters from API.
    suspend fun refreshFromApi() {
        try {

            val response = api.getCharacters()

            val entities = response.results.map { it.toEntity() }
            dao.clear()
            dao.insertAll(entities)

        } catch (e: Exception) {

        }
    }
}

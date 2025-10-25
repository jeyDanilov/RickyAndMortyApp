package com.example.data.repository

import com.example.data.local.CharacterDao
import com.example.data.remote.RickAndMortyApi
import com.example.data.remote.mapper.toDomain
import com.example.data.remote.mapper.toEntity
import com.example.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private val api: RickAndMortyApi,
    private val dao: CharacterDao
) {
    suspend fun getCharacters(): Flow<List<Character>> = flow {
        try {
            val response = api.getCharacters()
            val entities = response.results.map { it.toEntity() }
            dao.clear()
            dao.insertAll(entities)
            emit(entities.map { it.toDomain() })
        } catch (e: Exception) {
            emit(dao.getAll().first().map { it.toDomain() })
        }
    }
    suspend fun getFilteredCharacters(status: String?, gender: String?, species: String?): List<Character> {
        return try {
            val response = api.getFilteredCharacters(status, gender, species)
            val entities = response.results.map { it.toEntity() }
            entities.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

}

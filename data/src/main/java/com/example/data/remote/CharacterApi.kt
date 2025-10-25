package com.example.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit interface for communicating with the Rick and Morty REST API.
interface RickAndMortyApi {

    // Fetches all characters from the API.
    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    // Fetches characters filtered by optional status, gender, and species.
    @GET("character")
    suspend fun getFilteredCharacters(
        @Query("status") status: String?,
        @Query("gender") gender: String?,
        @Query("species") species: String?
    ): CharacterResponse

    // Base URL used for all API requests.
    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}

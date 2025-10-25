package com.example.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("character")
    suspend fun getFilteredCharacters(
        @Query("status") status: String?,
        @Query("gender") gender: String?,
        @Query("species") species: String?
    ): CharacterResponse

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}

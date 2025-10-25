package com.example.data.remote

// Data Transfer Object representing a character from the Rick and Morty API.
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: originDto,
    val location: locationDto,
    val image: String,
    val episode: List<String>
)

// DTO for origin location (nested inside CharacterDto).
data class originDto(val name: String, val url: String)

// DTO for current location (nested inside CharacterDto).
data class locationDto(val name: String, val url: String)



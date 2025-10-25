package com.example.data.remote

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

data class originDto(val name: String, val url: String)


data class locationDto(val name: String, val url: String)



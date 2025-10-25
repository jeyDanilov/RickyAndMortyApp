package com.example.data.remote

// Represents the full API response for character queries.
data class CharacterResponse(
    val info: PageInfoDto,
    val results: List<CharacterDto>
)

// Contains pagination details for navigating through API results.
data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

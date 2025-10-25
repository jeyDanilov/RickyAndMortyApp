package com.example.domain.model

// Domain model representing a character used across the app (UI, use cases, etc.).
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String,
    val episodeCount: Int
)
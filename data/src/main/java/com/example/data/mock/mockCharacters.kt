package com.example.data.mock

import com.example.domain.model.Character

fun mockCharacters(): List<Character> = listOf(
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = "Earth (C-137)",
        location = "Earth (Replacement Dimension)",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodeCount = 2
    ),
    Character(
        id = 183,
        name = "Johnny Depp",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = "Earth (C-500A)",
        location = "Earth (C-500A)",
        image = "https://rickandmortyapi.com/api/character/avatar/183.jpeg",
        episodeCount = 1
    ),
    Character(
        id = 183,
        name = "Toxic Rick",
        status = "Dead",
        species = "Humanoid",
        type = "Rick's Toxic Side",
        gender = "Male",
        origin = "Earth (C-500A)",
        location = "Earth (C-500A)",
        image = "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
        episodeCount = 1
    ),
    Character(
        id = 183,
        name = "Morty Smith",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        origin = "Earth",
        location = "Earth (C-500A)",
        image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
        episodeCount = 1
    )
)

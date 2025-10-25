package com.example.data.remote.mapper

import com.example.data.local.CharacterEntity
import com.example.data.remote.CharacterDto
import com.example.domain.model.Character

// Maps a CharacterDto (from API) to a CharacterEntity (for local database).
fun CharacterDto.toEntity() = CharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    originName = origin.name,
    locationName = location.name,
    image = image,
    episodeCount = episode.size
)

// Maps a CharacterDto (from API) directly to a domain model Character.
fun CharacterDto.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.name,
    location = location.name,
    image = image,
    episodeCount = episode.size
)

// Maps a CharacterEntity (from database) to a domain model Character.
fun CharacterEntity.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = originName,
    location = locationName,
    image = image,
    episodeCount = episodeCount
)

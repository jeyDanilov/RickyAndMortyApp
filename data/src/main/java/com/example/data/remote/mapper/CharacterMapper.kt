package com.example.data.remote.mapper

import com.example.data.local.CharacterEntity
import com.example.data.remote.CharacterDto
import com.example.domain.model.Character

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
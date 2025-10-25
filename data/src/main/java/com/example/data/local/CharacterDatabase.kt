package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

// Defines a Room database with CharacterEntity as the table schema.
@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    // Exposes the DAO for performing character-related database operations.
    abstract fun characterDao(): CharacterDao
}
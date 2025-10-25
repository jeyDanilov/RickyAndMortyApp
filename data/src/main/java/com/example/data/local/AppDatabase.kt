package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

// Declares a Room database with CharacterEntity as the only table.
@Database(entities = [CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    // Exposes the DAO for accessing character-related database operations.
    abstract fun characterDao(): CharacterDao
}

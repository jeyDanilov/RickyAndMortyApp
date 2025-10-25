package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.CharacterEntity
import kotlinx.coroutines.flow.Flow


// DAO interface for accessing character data in the Room database.
@Dao
interface CharacterDao {

    // Returns characters matching optional filters (null means ignore that filter).
    @Query(
        """
    SELECT * FROM characters
    WHERE (:status IS NULL OR status = :status)
      AND (:gender IS NULL OR gender = :gender)
      AND (:species IS NULL OR species = :species)
"""
    )
    suspend fun getFiltered(
        status: String?,
        gender: String?,
        species: String?
    ): List<CharacterEntity>

    // Returns all characters as a reactive Flow.
    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<CharacterEntity>>

    // Returns a character by its ID, or null if not found.
    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getById(id: Int): CharacterEntity?

    // Inserts a list of characters, replacing duplicates by ID.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    // Searches characters by name using a LIKE query.
    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%'")
    suspend fun searchByName(query: String): List<CharacterEntity>

    // Deletes all characters from the table.
    @Query("DELETE FROM characters")
    suspend fun clear()
}

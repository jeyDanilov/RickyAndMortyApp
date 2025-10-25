package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.CharacterEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CharacterDao {

    @Query("""
    SELECT * FROM characters
    WHERE (:status IS NULL OR status = :status)
      AND (:gender IS NULL OR gender = :gender)
      AND (:species IS NULL OR species = :species)
""")
    suspend fun getFiltered(status: String?, gender: String?, species: String?): List<CharacterEntity>

    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getById(id: Int): CharacterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%'")
    suspend fun searchByName(query: String): List<CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clear()
}
package jmmh.m513.movie5.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jmmh.m513.movie5.data.database.entities.QuoteEntity

@Dao
interface QuoteDao {
    @Query("SELECT*FROM quote_Table ORDER BY author DESC")
    suspend fun getAllQuotes(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<QuoteEntity>)
    @Query("DELETE FROM quote_Table")
    suspend fun deleteAllQuotes()
}
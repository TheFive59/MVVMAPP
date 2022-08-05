package jmmh.m513.movie5.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jmmh.m513.movie5.data.database.dao.QuoteDao
import jmmh.m513.movie5.data.database.entities.QuoteEntity

@Database(entities =[QuoteEntity::class],version =1)
abstract class QuoteDatabase:RoomDatabase() {
    abstract fun getQuotesDao(): QuoteDao
}
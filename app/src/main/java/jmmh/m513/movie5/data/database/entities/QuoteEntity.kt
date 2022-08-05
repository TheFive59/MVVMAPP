package jmmh.m513.movie5.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jmmh.m513.movie5.domain.model.Quote

@Entity(tableName = "quote_Table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "quote") val quote: String,
    @ColumnInfo(name = "author") val author: String
)
fun Quote.toDatabase()=QuoteEntity(quote=quote, author=author)
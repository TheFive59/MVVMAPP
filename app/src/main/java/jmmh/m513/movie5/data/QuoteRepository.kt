package jmmh.m513.movie5.data

import jmmh.m513.movie5.data.database.dao.QuoteDao
import jmmh.m513.movie5.data.database.entities.QuoteEntity
import jmmh.m513.movie5.data.network.QuoteService
import jmmh.m513.movie5.domain.model.Quote
import jmmh.m513.movie5.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {
    //Backend-Servidor


    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response = api.getQuotes()
        //Funcion con mapper
        return response.map {
            it.toDomain()
        }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote> {
        val response: List<QuoteEntity> =  quoteDao.getAllQuotes()
        return response.map { it.toDomain() }

    }

    suspend fun insertQuote(quotes:List<QuoteEntity>) {
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}
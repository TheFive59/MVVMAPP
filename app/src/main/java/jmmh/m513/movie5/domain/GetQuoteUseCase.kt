package jmmh.m513.movie5.domain

import jmmh.m513.movie5.data.QuoteRepository
import jmmh.m513.movie5.data.database.entities.toDatabase

import jmmh.m513.movie5.domain.model.Quote

import javax.inject.Inject

//Es la logica del negocio
//Recuperamos de data la informacion

class GetQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
){
    //invoke nos permite invocar desde otra clase los metodos que qeui se implemetnan
    //Tenemos que retornar lo que nos pide el caso de uso
    suspend operator fun invoke():List<Quote> {
        val quotes = repository.getAllQuotesFromApi()
        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuote(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }

    }
}
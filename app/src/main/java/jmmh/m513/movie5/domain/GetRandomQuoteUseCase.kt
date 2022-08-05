package jmmh.m513.movie5.domain


import jmmh.m513.movie5.data.QuoteRepository
import jmmh.m513.movie5.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {
    suspend operator fun invoke(): Quote? {
        //Obtener todos los datos y generar un numero random
        val quote = repository.getAllQuotesFromDatabase()
        if (!quote.isNullOrEmpty()) {
            //0 Primera posicion - 1 es para leer arreglos
            val randomNumber = (quote.indices).random()
            //Simplificado
            //val=(quotes.indices).random()
            return quote[randomNumber]
        }
        return null
    }
}
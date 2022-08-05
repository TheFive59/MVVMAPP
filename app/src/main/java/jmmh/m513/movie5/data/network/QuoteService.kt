package jmmh.m513.movie5.data.network

import jmmh.m513.movie5.core.RetrofitHelper
import jmmh.m513.movie5.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

//Saber de donde vamos a llamar los datos
class QuoteService @Inject constructor(private val api: QuoteApiClient) {
    //Nos devuelve la lista de los dato
    suspend fun getQuotes(): List<QuoteModel> {
        //Nos devuelve un response de una lista de quotes (Frases)
        //Se ejecuta toda la llamada en un hilo secundario para no saturar la interfaz
        return withContext(Dispatchers.IO) {
            val response = api.getAllQuotes()
            response.body() ?: emptyList()
        }


    }
}
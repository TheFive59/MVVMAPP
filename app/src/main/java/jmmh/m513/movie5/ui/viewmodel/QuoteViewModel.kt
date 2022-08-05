package jmmh.m513.movie5.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jmmh.m513.movie5.domain.GetQuoteUseCase
import jmmh.m513.movie5.domain.GetRandomQuoteUseCase
import jmmh.m513.movie5.domain.model.Quote
import kotlinx.coroutines.launch
import javax.inject.Inject

//Se desentiende de lo que tiene que pasar solamente llama a su caso de uso y genera lo que necesitamos
@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase: GetQuoteUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {
    val quoteModel = MutableLiveData<Quote>()

    //Mostrar u ocultar la progress bar
    val isLoading = MutableLiveData<Boolean>()
    fun onCreate() {
        //LLamar al caso de uso y almacenar la info
        //viewModelScope Nos permite crear una corrutina de control automatico
        viewModelScope.launch {
            //Como hare algo que va a tardar tengo que mostrar progress bar
            isLoading.postValue(true)
            //Result llama a caso de uso. caso de uso llama reposi, repo obtiene la info, sevicio retrpfit para listar quotes
            val result = getQuoteUseCase()
            if (!result.isNullOrEmpty()) {
                //Fijamos a live data en la interfaz
                //Si no es nulo ni vacio tomar la primera y ponerla
                quoteModel.postValue(result[0])
                //Cuando termine de cargar cerramos progress bar
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            //val currentQuote= QuoteProvider.random()
            val quote = getRandomQuoteUseCase()
            if (quote != null) {
                quoteModel.postValue(quote)
            }
            //quoteModel.postValue(currentQuote)
            isLoading.postValue(false)
        }
    }
}
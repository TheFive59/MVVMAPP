package jmmh.m513.movie5.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import jmmh.m513.movie5.data.QuoteRepository
import jmmh.m513.movie5.domain.model.Quote
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuoteUseCaseTest{
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getQuoteUseCase: GetQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuoteUseCase = GetQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()
        //When
        getQuoteUseCase()
        //Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }
    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Quote("Déjame un comentario", "AristiDevs"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns myList
        //When
        val response = getQuoteUseCase()
        //Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuote(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(response == myList)
    }
}
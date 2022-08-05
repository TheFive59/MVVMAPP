package jmmh.m513.movie5.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import jmmh.m513.movie5.data.QuoteRepository
import jmmh.m513.movie5.data.database.dao.QuoteDao
import jmmh.m513.movie5.domain.model.Quote
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest {
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository
    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assert(response == null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {
        //Given
        val quoteList = listOf(Quote("Hola", "Test con quote no null"))
        //Cada vez
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assert(response == quoteList.first())
    }

}


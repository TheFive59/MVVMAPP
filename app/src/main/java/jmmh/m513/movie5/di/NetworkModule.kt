package jmmh.m513.movie5.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jmmh.m513.movie5.core.Constants
import jmmh.m513.movie5.core.RetrofitHelper
import jmmh.m513.movie5.data.network.QuoteApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//Porveen dependencias
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //Mnatener una unica instancia de retrofit
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideQuoteApiClient(retrofit: Retrofit):QuoteApiClient{
        return retrofit.create(QuoteApiClient::class.java)
    }
}
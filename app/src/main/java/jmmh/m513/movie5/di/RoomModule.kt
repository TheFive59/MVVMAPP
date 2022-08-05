package jmmh.m513.movie5.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jmmh.m513.movie5.core.Constants
import jmmh.m513.movie5.data.database.QuoteDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    //Inyectar base de datos
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)=
        Room.databaseBuilder(context,QuoteDatabase::class.java,Constants.QUOTE_DATABASE_NAME).build()
    @Singleton
    @Provides
    fun provideQuoteDao(db:QuoteDatabase)=db.getQuotesDao()
}
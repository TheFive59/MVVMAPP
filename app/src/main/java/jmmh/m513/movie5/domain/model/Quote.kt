package jmmh.m513.movie5.domain.model

import jmmh.m513.movie5.data.database.entities.QuoteEntity
import jmmh.m513.movie5.data.model.QuoteModel

//Maper

data class Quote (val quote:String,val author:String)
//Funcion de estension
fun QuoteModel.toDomain()=Quote(quote,author)
fun QuoteEntity.toDomain()=Quote(quote,author)

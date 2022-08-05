package jmmh.m513.movie5.core

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreads(@Url url:String):Response<DogResponse>
}
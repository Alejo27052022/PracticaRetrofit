package com.example.project_practica.service

import com.example.project_practica.data.Datos
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService{
    @GET("character")
    suspend fun  getMorty(): CharacterResponse
}

data class CharacterResponse(
    @SerializedName("results") val results: List<Datos>
)

object RetrofitInstance {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitService(): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}
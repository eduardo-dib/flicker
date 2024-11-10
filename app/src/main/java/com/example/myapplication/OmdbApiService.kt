package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Definindo a interface para as requisições à API OMDb
interface OmdbApiService {
    // Aqui usamos a chave da API OMDb e o parâmetro 's' para fazer a pesquisa por título
    @GET("?3eb04ca2")  // Substitua pela sua chave
    fun searchMovies(@Query("s") title: String): Call<MovieSearchResponse>
}
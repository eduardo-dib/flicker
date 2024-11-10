package com.example.myapplication

data class MovieSearchResponse(
    val Search: List<Movie>,  // Lista de filmes encontrados
    val Response: String      // Indicador de sucesso ou falha
)

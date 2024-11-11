package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieSearchScreen(this) // Passamos o contexto atual
        }
    }
}

@Composable
fun MovieSearchScreen(context: ComponentActivity) { // Recebe o contexto como parâmetro
    var query by remember { mutableStateOf("") }
    var movies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    val apiService = remember { createApiService() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Campo de pesquisa
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                )

                // Botão de busca
                Button(
                    onClick = {
                        if (query.isNotEmpty()) {
                            isLoading = true
                            searchMovies(apiService, query, context) { result ->
                                movies = result
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Buscar")
                }

                // Exibir os filmes encontrados
                if (isLoading) {
                    Text("Carregando...")
                } else if (movies.isNotEmpty()) {
                    LazyColumn {
                        items(movies) { movie ->
                            MovieItem(movie = movie)
                        }
                    }
                } else {
                    Text("Nenhum filme encontrado")
                }
            }
        }
    )
}

@Composable
fun MovieItem(movie: Movie) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(movie.Title, style = MaterialTheme.typography.titleLarge)
        Text(movie.Year)
        val painter: Painter = rememberImagePainter(movie.Poster)
        Image(painter = painter, contentDescription = "Poster de ${movie.Title}", modifier = Modifier.size(100.dp))
    }
}

fun createApiService(): OmdbApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(OmdbApiService::class.java)
}

fun searchMovies(apiService: OmdbApiService, query: String, context: ComponentActivity, callback: (List<Movie>) -> Unit) {
    apiService.searchMovies(query).enqueue(object : retrofit2.Callback<MovieSearchResponse> {
        override fun onResponse(
            call: Call<MovieSearchResponse>,
            response: retrofit2.Response<MovieSearchResponse>
        ) {
            if (response.isSuccessful) {
                callback(response.body()?.Search ?: emptyList())
            } else {
                Toast.makeText(context, "Erro: ${response.message()}", Toast.LENGTH_SHORT).show()
                callback(emptyList())
            }
        }

        override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
            Toast.makeText(context, "Erro ao buscar filmes: ${t.message}", Toast.LENGTH_SHORT).show()
            callback(emptyList())
        }
    })
}

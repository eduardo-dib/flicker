package com.example.myapplication
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilmesViewModel(private val repository: FilmesRepository) : ViewModel() {
    fun inserirFilme(filme: Filme) = viewModelScope.launch {
        repository.inserirFilme(filme)
    }


}

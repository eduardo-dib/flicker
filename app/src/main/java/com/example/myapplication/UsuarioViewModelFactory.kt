package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// Criação da factory para fornecer o UsuarioRepository no ViewModel
class UsuarioViewModelFactory(private val repository: UsuarioRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Aqui é onde o ViewModel é criado e a dependência é passada
        if (modelClass.isAssignableFrom(UsuarioViewModel::class.java)) {
            return UsuarioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
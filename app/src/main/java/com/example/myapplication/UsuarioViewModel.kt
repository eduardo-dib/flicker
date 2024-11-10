package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {

    fun inserirUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.inserir(usuario)
        }
    }

    fun obterUsuarioPorId(id: Int, callback: (Usuario?) -> Unit) {
        viewModelScope.launch {
            callback(repository.obterUsuarioPorId(id))
        }
    }

    fun obterTodosUsuarios(callback: (List<Usuario>) -> Unit) {
        viewModelScope.launch {
            callback(repository.obterTodosUsuarios())
        }
    }

    fun atualizarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.atualizarUsuario(usuario)
        }
    }

    fun deletarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.deletarUsuario(usuario)
        }
    }
}
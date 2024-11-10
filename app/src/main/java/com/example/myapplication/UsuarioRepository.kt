package com.example.myapplication

class UsuarioRepository(private val usuarioDao: UsuarioDao) {

    suspend fun inserir(usuario: Usuario) = usuarioDao.inserirUsuario(usuario)

    suspend fun obterUsuarioPorId(id: Int) = usuarioDao.obterUsuarioPorId(id)

    suspend fun obterTodosUsuarios() = usuarioDao.obterTodosUsuarios()

    suspend fun atualizarUsuario(usuario: Usuario) = usuarioDao.atualizarUsuario(usuario)

    suspend fun deletarUsuario(usuario: Usuario) = usuarioDao.deletarUsuario(usuario)
}
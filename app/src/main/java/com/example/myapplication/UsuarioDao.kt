package com.example.myapplication
import androidx.room.*

@Dao
interface UsuarioDao {
    @Insert
    suspend fun inserirUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun obterUsuarioPorId(id: Int): Usuario?

    @Query("SELECT * FROM usuarios")
    suspend fun obterTodosUsuarios(): List<Usuario>

    @Update
    suspend fun atualizarUsuario(usuario: Usuario)

    @Delete
    suspend fun deletarUsuario(usuario: Usuario)
}

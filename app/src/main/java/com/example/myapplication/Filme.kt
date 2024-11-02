package com.example.myapplication
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "filmes",
    foreignKeys = [ForeignKey(
        entity = ListaFilmes::class,
        parentColumns = ["id"],
        childColumns = ["listaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Filme(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String,
    val anoLancamento: String,
    val status: String, // "não assistido" ou "assistido"
    val listaId: Int? = null // ID da lista associada
)
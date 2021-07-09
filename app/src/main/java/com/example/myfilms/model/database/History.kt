package com.example.myfilms.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //сущность, таблица внутри БД
data class History (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val idMovie: Int,
    val movieName: String,
    val overview: String?,
    val original_language: String?,
    val year: String,
)
package com.example.myfilms.model.rest_entities

import java.io.Serializable

data class MoviesDTO(
        val id: Int?,
        val name: String?,
        val title: String?,
        val imdb_id: String?,
        val overview: String?,
        val original_language: String?,
        val release_date: String?,
        val vote_average: Number?,
) : Serializable

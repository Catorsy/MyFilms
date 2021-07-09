package com.example.myfilms.model.rest.rest_entities

import java.io.Serializable

data class MoviesDTO(
        val id: Int?,
        val title: String?,
        val imdb_id: String?,
        val overview: String?,
        val original_language: String?,
        val release_date: String?,
        val vote_average: Number?,
        val poster_path: String?,
        val adult: Boolean?,
) : Serializable

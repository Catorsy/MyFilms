package com.example.myfilms.model

data class Movies (
        val name: String,
        val overview: String?,
        val original_language: String,
        val release_date: String,
        val vote_average: Number
        )

fun getDefaultMovie() = Movies("Области тьмы",
"История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
"Английский",
        "2015",
        8
)

// здесь будет список захардкоженных фильмов
fun getListOfMovies() : List <Movies> {
        return listOf(
                //TODO
        )
}
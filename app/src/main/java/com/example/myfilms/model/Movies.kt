package com.example.myfilms.model

data class Movies (
        val name: String = "Области тьмы",
        val overview: String? = "История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
        val original_language: String = "Английский",
        val release_date: String = "2015",
        val vote_average: Number = 8
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
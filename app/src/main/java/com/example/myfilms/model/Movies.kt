package com.example.myfilms.model

import java.io.Serializable

data class Movies (
        val name: String = "Области тьмы",
        val overview: String? = "История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
        val original_language: String = "Английский",
        val release_date: String = "2015",
        val vote_average: Number = 8,
        val imdb_id : String? = "dark" //это что? Вот сюда записывют постер?
        ) :Serializable

//пусть будет, вдруг пригодится
fun getDefaultMovie() = Movies("Области тьмы",
"История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
"Английский",
        "2015",
        8
)

fun getListOfMovies() : List <Movies> {
        return listOf(
                Movies("Форрест Гамп", "Полувековая история США глазами чудака из Алабамы. Абсолютная классика Роберта Земекиса с Томом Хэнксом.",
        "Аншлийский", "1994", 8.9, "forrest" ),
                Movies ("Престиж", "Ретрофантастика Кристофера Нолана об опасной вражде двух иллюзионистов. Бонус — Дэвид Боуи в роли Николы Теслы.",
                "Английский", "2006", 8.5, "prestige"),
                Movies("Облако", "Маленький немецкий городок Шлитц объят паникой - на расположенной неподалёку атомной электростанции произошла авария, и всю округу накрывает радиоактивное облако...",
                "Немецкий", "2006", 7.7, "dieWolke"),
                Movies("T-34", "Лейтенант готовит побег из концлагеря на танке. Брутальный патриотический блокбастер с Александром Петровым.",
                "Русский", "2018", 6.5, "t34"),
                Movies ("Вторая жизнь Уве", "Сварливый вдовец-придира находит смысл жизни с появлением новых соседей. Добрый фильм по шведскому бестселлеру.",
                "Шведский", "2015", 8, "uve")
        )
}
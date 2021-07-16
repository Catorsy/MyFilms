package com.example.myfilms.model

import android.os.Parcelable
import com.example.myfilms.R
import java.io.Serializable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
        val id: Int? = 51876,
        val title: String? = "Области тьмы",
        val overview: String? = "История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
        val original_language: String? = "Английский",
        val release_date: String? = "2015",
        val vote_average: Number? = 8,
        val numberPicture: Int? = 0,
        val poster_path: String? = "/sLmyLSIZZBhHWZWAkM4hSRlpd0.jpg", //нет, постер записывают сюда
        val adult: Boolean? = false,

) : Serializable, Parcelable

fun getListOfMovies() = listOf(
        Movies(51876, "Области тьмы", "История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
                "Английский", "2015", 8.4, R.drawable.dark, null, false),
        Movies(9475,  "Запах женщины", "Слепой полковник едет кутить в Нью-Йорк под присмотром бедного студента. Аль Пачино заслуженно берет «Оскар».",
                "Английский", "1992", 8.4, R.drawable.secret, null, false),
        Movies(13,  "Форрест Гамп", "Полувековая история США глазами чудака из Алабамы. Абсолютная классика Роберта Земекиса с Томом Хэнксом.",
                "Английский", "1994", 8.9, R.drawable.forrest, null, false),
        Movies(1124,"Престиж", "Ретрофантастика Кристофера Нолана об опасной вражде двух иллюзионистов. Бонус — Дэвид Боуи в роли Николы Теслы.",
                "Английский", "2006", 8.5, R.drawable.prestige, null, false),
        Movies(7456,  "Облако", "Маленький немецкий городок Шлитц объят паникой - на расположенной неподалёку атомной электростанции произошла авария, и всю округу накрывает радиоактивное облако...",
                "Немецкий", "2006", 7.7, R.drawable.diewolke, null, false),
        Movies(505954,  "Т-34", "Лейтенант готовит побег из концлагеря на танке. Брутальный патриотический блокбастер с Александром Петровым.",
                "Русский", "2018", 6.5, R.drawable.t34, null, false),
        Movies(348678, "Вторая жизнь Уве", "Сварливый вдовец-придира находит смысл жизни с появлением новых соседей. Добрый фильм по шведскому бестселлеру.",
                "Шведский", "2015", 8, R.drawable.uve, null, false),
        Movies(1738, "Пророк", "«If you can see the future, you can save it».",
                "Английский", "2007", 7.1, R.drawable.sorc, null, false),
        Movies(1726, "Железный человек", "Попав в плен, Тони Старк изобретает суперкостюм и спасает мир. Блокбастер, запустивший киновселенную Marvel.",
                "Английский", "2008", 7.9, R.drawable.iron, null, false),
        Movies(144,  "Небо над Берлином", "«There are angels on the streets of Berlin»",
                "Немецкий", "1987", 7.9, R.drawable.sky, null, false),
        Movies(4255,  "Итальянский жеребец", "Первый фильм Сильвестра Сталоне!",
                "Английский", "1970", 5.1, R.drawable.horse, null, true),
        Movies(245891,  "Джон Уик", "Перестрелки, взрывы, безумные гонки и убитая собака: Киану Ривз беспощадно мстит зарвавшимся бандитам.",
                "Английский", "2014", 6.9, R.drawable.john, null, false),
        Movies(424,  "Список Шиндлера", "История немецкого промышленника, спасшего тысячи жизней во время Холокоста. Драма Стивена Спилберга.",
                "Английский", "1993", 8.8, R.drawable.schindler, null, false),
)


package com.example.myfilms.model

import android.os.Parcelable
import com.example.myfilms.R
import java.io.Serializable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
        val id: Int?,
        val name: String? = "Области тьмы",
        val title: String? = "Области тьмы",
        val overview: String? = "История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
        val original_language: String? = "Английский",
        val release_date: String? = "2015",
        val vote_average: Number? = 8,
        val numberPicture: Int? = 0,
        val imdb_id: String? = "dark", //это что? Вот сюда записывют постер?
) : Serializable, Parcelable

fun getListOfMovies() = listOf(
        Movies(0, "51876-limitless", "Области тьмы", "История о человеке, которому удаётся максимально раскрыть возможности своего мозга.",
                "Английский", "2015", 8.4, R.drawable.dark),
        Movies(1, "9475-scent-of-a-woman", "Запах женщины", "Слепой полковник едет кутить в Нью-Йорк под присмотром бедного студента. Аль Пачино заслуженно берет «Оскар».",
                "Английский", "1992", 8.4, R.drawable.secret),
        Movies(2, "13-forrest-gump", "Форрест Гамп", "Полувековая история США глазами чудака из Алабамы. Абсолютная классика Роберта Земекиса с Томом Хэнксом.",
                "Английский", "1994", 8.9, R.drawable.forrest),
        Movies(3, "1124-the-prestige", "Престиж", "Ретрофантастика Кристофера Нолана об опасной вражде двух иллюзионистов. Бонус — Дэвид Боуи в роли Николы Теслы.",
                "Английский", "2006", 8.5, R.drawable.prestige),
        Movies(4, "7456-die-wolke", "Облако", "Маленький немецкий городок Шлитц объят паникой - на расположенной неподалёку атомной электростанции произошла авария, и всю округу накрывает радиоактивное облако...",
                "Немецкий", "2006", 7.7, R.drawable.diewolke),
        Movies(5, "505954-34", "Т-34", "Лейтенант готовит побег из концлагеря на танке. Брутальный патриотический блокбастер с Александром Петровым.",
                "Русский", "2018", 6.5, R.drawable.t34),
        Movies(6, "348678-en-man-som-heter-ove", "Вторая жизнь Уве", "Сварливый вдовец-придира находит смысл жизни с появлением новых соседей. Добрый фильм по шведскому бестселлеру.",
                "Шведский", "2015", 8, R.drawable.uve),
        Movies(7, "1738-next", "Пророк", "«If you can see the future, you can save it».",
                "Английский", "2007", 7.1, R.drawable.sorc),
        Movies(8, "1726-iron-man", "Железный человек", "Попав в плен, Тони Старк изобретает суперкостюм и спасает мир. Блокбастер, запустивший киновселенную Marvel.",
                "Английский", "2008", 7.9, R.drawable.iron),
        Movies(9, "144-der-himmel-ber-berlin", "Небо над Берлином", "«There are angels on the streets of Berlin»",
                "Немецкий", "1987", 7.9, R.drawable.sky),
)
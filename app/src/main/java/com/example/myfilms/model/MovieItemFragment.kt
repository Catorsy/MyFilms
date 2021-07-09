package com.example.myfilms.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfilms.databinding.FragmentMovieItemBinding
import com.example.myfilms.model.repository.RUSSIAN_LANGUAGE
import com.example.myfilms.model.rest.rest_entities.ApiUtils
import com.example.myfilms.model.rest.rest_entities.MoviesDTO
import com.example.myfilms.model.rest.rest_entities.MoviesRepo
import com.example.myfilms.viewModel.AppState
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.myfilms.model.Movies as Movies

const val ARG_MOVIE = "ARG_MOVIE"

class MovieItemFragment : Fragment() {

    private lateinit var binding: FragmentMovieItemBinding
    private val viewModel: MovieItemViewModel by viewModel()
    private var movieData: Movies? = null
    private lateinit var moviesBundle: Movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieData = it.getSerializable(ARG_MOVIE) as Movies
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    //а как быть с сериалайзабл?!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initView()

        val movies = arguments?.getParcelable<Movies>(ARG_MOVIE) //TODO а как сделать с сериалайзбл?
        movies?.let{
            with (binding) {
                val moviesName = it.id
                viewModel.liveDataToObserve.observe(viewLifecycleOwner, {appState -> //подписываемся на обновления
                    when (appState) {
                        is AppState.Error -> {
                            Snackbar.make(linearDetails, "Error!", Snackbar.LENGTH_LONG).show()
                            waitForMe.visibility = View.GONE
                        }
                        AppState.Loading -> binding.waitForMe.visibility = View.VISIBLE
                        is AppState.Success -> {
                            waitForMe.visibility = View.GONE
                            itemName.text = appState.moviesData.first().title
                            score.text = appState.moviesData.first().vote_average?.toString()
                            year.text = appState.moviesData.first().release_date
                            language.text = appState.moviesData.first().original_language
                            description.text = appState.moviesData.first().overview
                            Picasso.get().load("${ApiUtils.BASE_IMAGE_SITE}${appState.moviesData.first().poster_path}")
                                .into(movieImage)
                        }
                    }
                })
                        //жизнь до ретрофита
                if (moviesName != null) {
                    viewModel?.loadData(moviesName)
                }
                Picasso.get().load("${ApiUtils.BASE_IMAGE_SITE}${it.poster_path}")
                    .into(movieImage)

//                MoviesRepo.api.getMovie(it.id, RUSSIAN_LANGUAGE) //раньше тут был ключ
//                    .enqueue(object : Callback<MoviesDTO> {
//                        override fun onResponse(
//                            call: Call<MoviesDTO>,
//                            response: Response<MoviesDTO>
//                        ) {
//                            if (response.isSuccessful) {
//                                val movie = response.body()
//                                val converted = movie?.let {
//                                    Movies (
//                                        id = it.id,
//                                        title = it.title,
//                                        overview = it.overview,
//                                        original_language = it.original_language,
//                                        release_date = it.release_date,
//                                        vote_average = it.vote_average,
//                                        poster_path = it.poster_path,
//                                        adult = it.adult,
//                                            )
//                                } ?: Movies() //просто по дефолту
//                                waitForMe.visibility = View.GONE
//                                itemName.text = converted.title
//                                score.text = converted.vote_average.toString()
//                                year.text = converted.release_date
//                                language.text = converted.original_language
//                                description.text = converted.overview
//                                Picasso.get().load("${ApiUtils.BASE_IMAGE_SITE}${converted.poster_path}")
//                                    .into(movieImage)
//                            }
//                        }
//
//                        //вызывается, если что-то случилось на нашей стороне, а не
//                        //не стороне сервера. Напр, запрос не прошел, исчез интернет.
//                        override fun onFailure(call: Call<MoviesDTO>, t: Throwable) {
//                            Toast.makeText(context, "Мы не смогли отправить запрос.", Toast.LENGTH_LONG).show()
//                        }
//
//                    })
            }
        }
    }

//    private fun initView() = with (binding) {
//        description.text = movieData?.overview
//        itemName.text = movieData?.title
//        language.text = movieData?.original_language
//        year.text = movieData?.release_date
//        score.text = movieData?.vote_average.toString()
//        movieData?.numberPicture?.let { movieImage.setImageResource(it) }
//    }
//
//    //отображаем полученные данные
//    private fun displayMovies(moviesDTO: MoviesDTO) = with (binding){
//            linearDetails.visibility = View.VISIBLE
//            waitForMe.visibility = View.GONE
//            val name = moviesBundle.title //будем запрашивать фильм по имени
//            itemName.text = name
//            score.text = moviesDTO?.vote_average.toString()
//            year.text = moviesDTO?.release_date
//            language.text = moviesDTO?.original_language
//            description.text = moviesDTO?.overview
//            movieImage.setImageResource(R.drawable.prestige) //TODO картинки!
//    }


    companion object {
        private const val api_key = "697bdb3bdc1a9dfcf325c28b417a9ba6"

        @JvmStatic
        fun newInstance(movieData: Movies?) =
                MovieItemFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_MOVIE, movieData)
                    }
                }
    }
}
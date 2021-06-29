package com.example.myfilms.model

import android.graphics.Movie
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfilms.R
import com.example.myfilms.databinding.FragmentMovieItemBinding
import com.example.myfilms.model.rest_entities.MoviesDTO
import com.example.myfilms.viewModel.AppState
import com.example.myfilms.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
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
        val movies = arguments?.getParcelable<Movies>(ARG_MOVIE)
        movies?.let{
            with (binding) {
                val moviesName = it.name
                itemName.text = moviesName //сеттим что есть
                viewModel.liveDataToObserve.observe(viewLifecycleOwner, {appState -> //подписываемся на обновления
                    when (appState) {
                        is AppState.Error -> {
                            Snackbar.make(linearDetails, "Error!", Snackbar.LENGTH_LONG).show()
                            waitForMe.visibility = View.GONE
                        }
                        AppState.Loading -> binding.waitForMe.visibility = View.VISIBLE
                        is AppState.Success -> {
                            waitForMe.visibility = View.GONE
                            //itemName.text = appState.moviesData[0].name
                            score.text = appState.moviesData[0].vote_average?.toString()
                            year.text = appState.moviesData[0].release_date
                            language.text = appState.moviesData[0].original_language
                            description.text = appState.moviesData[0].overview
                        }
                    }
                })
                if (moviesName != null) {
                    viewModel?.loadData(moviesName)
                }
            }
        }
    }

    //ДЗ ПОЧТИ доделана, не хватает проверить работоспособность.

    private fun initView() = with (binding) {
        description.text = movieData?.overview
        itemName.text = movieData?.name
        language.text = movieData?.original_language
        year.text = movieData?.release_date
        score.text = movieData?.vote_average.toString()
        movieData?.numberPicture?.let { movieImage.setImageResource(it) }
    }

    //отображаем полученные данные
    private fun displayMovies(moviesDTO: MoviesDTO) = with (binding){
            linearDetails.visibility = View.VISIBLE
            waitForMe.visibility = View.GONE
            val name = moviesBundle.name //будем запрашивать фильм по имени
            itemName.text = name
            score.text = moviesDTO.fact?.vote_average.toString()
            year.text = moviesDTO.fact?.release_date
            language.text = moviesDTO.fact?.original_language
            description.text = moviesDTO.fact?.overview
            movieImage.setImageResource(R.drawable.prestige) //TODO картинки!
    }

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
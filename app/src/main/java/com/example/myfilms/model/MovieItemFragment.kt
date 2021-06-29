package com.example.myfilms.model

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfilms.databinding.FragmentMovieItemBinding
import com.example.myfilms.model.rest_entities.MoviesDTO

class MovieItemFragment : Fragment() {

    private lateinit var binding: FragmentMovieItemBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with (binding) {
        description.text = movieData?.overview
        itemName.text = movieData?.name
        language.text = movieData?.original_language
        year.text = movieData?.release_date
        score.text = movieData?.vote_average.toString()
        movieData?.numberPicture?.let { movieImage.setImageResource(it) }
    }

    //отображаем полученные данные
    private fun displayMovies(moviesDTO: MoviesDTO) {
        with (binding) {
            linearDetails.visibility = View.VISIBLE
            waitForMe.visibility = View.GONE
            val movie = moviesBundle.
                    //если Вы дошли до этой строчки и она всё ещё есть, это значит, что я не успела доделать дз, а срок поджимает.
            //планирую сделать дз до среды включая среду, прошу посмотреть дз попозже.
        }
    }

    companion object {
        const val ARG_MOVIE = "ARG_MOVIE"
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
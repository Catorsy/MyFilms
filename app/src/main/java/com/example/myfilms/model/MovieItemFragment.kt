package com.example.myfilms.model

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myfilms.R
import com.example.myfilms.databinding.FragmentMovieItemBinding

class MovieItemFragment : Fragment() {

    private lateinit var binding: FragmentMovieItemBinding
    private var movieData: Movies? = null

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

    private fun initView() {
        binding.description.text = movieData?.overview
        binding.itemName.text = movieData?.name
        binding.language.text = movieData?.original_language
        binding.year.text = movieData?.release_date
        binding.score.text = movieData?.vote_average.toString()
        binding.movieImage.setImageResource(R.drawable.dark)
    }

    companion object {
        const val ARG_MOVIE = "ARG_FILM"

        @JvmStatic
        fun newInstance(movieData: Movies?) =
            MovieItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_MOVIE, movieData)
                }
            }
    }
}
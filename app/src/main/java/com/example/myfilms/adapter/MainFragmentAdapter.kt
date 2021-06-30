package com.example.myfilms.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myfilms.R
import com.example.myfilms.databinding.FragmentMovieItemBinding
import com.example.myfilms.model.Movies
import com.example.myfilms.view.MainFragment

class MainFragmentAdapter(private var onItemViewClickListener: MainFragment.OnItemViewClickListener?)
    : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var moviesData: List<Movies> = listOf()
    private lateinit var binding: FragmentMovieItemBinding

    fun setListOfMovies(data: List<Movies>) {
        moviesData = data
        notifyDataSetChanged()
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movies) = with(binding) {
            itemName.text = movie.title
            description.text = movie.overview
            language.text = movie.original_language
            score.text = movie.vote_average.toString()
            year.text = movie.release_date
            movie.numberPicture?.let { movieImage.setImageResource(it) }
            root.setOnClickListener {
                onItemViewClickListener?.onItemViewClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = FragmentMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount() = moviesData.size

    fun removeListener() {
        onItemViewClickListener = null
    }
}
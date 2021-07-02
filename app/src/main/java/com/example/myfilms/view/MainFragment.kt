package com.example.myfilms.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfilms.R
import com.example.myfilms.adapter.MainFragmentAdapter
import com.example.myfilms.databinding.MainFragmentBinding
import com.example.myfilms.viewModel.MainViewModel
import com.example.myfilms.viewModel.AppState
import com.example.myfilms.model.MovieItemFragment
import com.example.myfilms.model.Movies
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

//!!!!!
//Я заглушка, подождите меня, пожалуйста :) Дз к процесс изготовления

class MainFragment : Fragment() {

    val ARG_MOVIE = "ARG_MOVIE"
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()
    private val onListItemClickListener = object : OnItemViewClickListener {
        override fun onItemViewClick(movies: Movies) {
            activity?.supportFragmentManager?.let {
                val bundle = Bundle()
                bundle.putSerializable(ARG_MOVIE, movies)
                it.beginTransaction()
                        .replace(R.id.container, MovieItemFragment.newInstance(movies))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
            }
        }
    }
    private val adapter = MainFragmentAdapter(onListItemClickListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        val observer = Observer<AppState> { renderData(it) } //выполняет renderData сразу, как только LiveData обновляет данные
        with (viewModel) {
            getLiveData().observe(viewLifecycleOwner, observer) ////viewLifecycleOwner - универсальня ссылка на активити или фрагмент
            getMoviesFromLocalSource()
        }

    }

    private fun renderData(appState: AppState) = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        when (appState) {
            is AppState.Success -> {
                waitForIt.visibility = View.GONE
                adapter.setListOfMovies(appState.moviesData)
                Snackbar.make(recyclerView, getString(R.string.sucess), Snackbar.LENGTH_LONG).show()
            }
            is AppState.Loading -> {
                waitForIt.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                waitForIt.visibility = View.GONE
                Snackbar.make(recyclerView, getString(R.string.no_sucess), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    //интерфейс для передачи данных между адаптером списка и фрагментом
    interface OnItemViewClickListener {
        fun onItemViewClick(movies: Movies)
    }

    //следит за утечками, удаляет слушатель из адаптера
    override fun onDestroy() {
        adapter.removeListener()
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}


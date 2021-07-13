package com.example.myfilms.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
import kotlinx.android.synthetic.main.fragment_movie_item.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

var adult = false
private const val dataSetKey = "dataSetKey"

//сделаны пункты 3,4,1
class MainFragment : Fragment() {

    private val netReseiver : BroadcastReceiver = object  : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(context, "А теперь у нас перебои с сетью.", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        context?.registerReceiver(netReseiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onStop() {
        context?.unregisterReceiver(netReseiver)
        super.onStop()
    }

    //здесь мог бы быть ваш сервис
    //создаем ресивер, регистрируем его в онСтарт или онКреейт, отключаем в онСтоп или онДестрой
    //пишем метод со стартСервис

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

        adult = activity?.getPreferences(Context.MODE_PRIVATE)
            ?.getBoolean(dataSetKey, false) ?: false
        radio_adult_off.isChecked = !adult
        radio_adult_on.isChecked = adult
        initRadio()

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

    fun initRadio () {
        radio_adult_on.setOnClickListener {
            adult = true
            saveDataToDisk()
            Toast.makeText(context, getString(R.string.adult_on), Toast.LENGTH_LONG).show()
        }
        radio_adult_off.setOnClickListener {
            adult = false
            saveDataToDisk()
            Toast.makeText(context, getString(R.string.adult_off), Toast.LENGTH_LONG).show()
        }
    }

    private fun saveDataToDisk() {
        val editor = activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
        editor?.putBoolean(dataSetKey, adult)?.apply()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}


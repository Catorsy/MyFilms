package com.example.myfilms.framework.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfilms.R
import com.example.myfilms.databinding.MainFragmentBinding
import com.example.myfilms.model.AppState
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
       // lifecycle.addObserver(viewModel)
        val observer = Observer <AppState> {renderData (it)} //выполняет renderData сразу, как только LiveData обновляет данные
        viewModel.getLiveData().observe(viewLifecycleOwner, observer) ////viewLifecycleOwner - универсальня ссылка на активити или фрагмент
        viewModel.getMovies()
    }

    private fun renderData(appState : AppState) = with (binding) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        //сюда адаптер TODO
        Toast.makeText(context, "data", Toast.LENGTH_LONG).show()


    }

    private fun setData(moviesData: AppState) {

    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
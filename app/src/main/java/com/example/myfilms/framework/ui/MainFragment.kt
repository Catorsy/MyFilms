package com.example.myfilms.framework.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.myfilms.R
import com.example.myfilms.databinding.MainFragmentBinding


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

        val observer = Observer <Any> {renderData (it)} //выполняет renderData сразу, как только LiveData обновляет данные
        viewModel.getData().observe(viewLifecycleOwner, observer) ////viewLifecycleOwner - универсальня ссылка на активити или фрагмент
    }

    private fun renderData(data: Any?) {
        Toast.makeText(context, "data", Toast.LENGTH_LONG).show()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
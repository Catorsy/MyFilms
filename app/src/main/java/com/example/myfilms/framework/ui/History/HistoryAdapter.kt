package com.example.myfilms.framework.ui.History

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myfilms.databinding.ItemHistoryListBinding
import com.example.myfilms.model.Movies

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {
    private var data : List<Movies> = arrayListOf()

    fun setData(data: List<Movies>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder =
        RecyclerItemViewHolder(ItemHistoryListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HistoryAdapter.RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: ItemHistoryListBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Movies) = with(binding) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                recyclerViewItem.text =
                    String.format("%s %s", data.title, data.release_date)
                //"%s %d при форматировании с - строки, д - целые числа
                root.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Просмотрена информация по фильму: ${data.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
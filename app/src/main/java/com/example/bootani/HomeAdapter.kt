package com.example.bootani

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bootani.data.HomeItem
import com.example.bootani.databinding.ItemRowAtHomeBinding

class HomeAdapter(private val items: List<HomeItem>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(private val binding: ItemRowAtHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HomeItem) {
            binding.tvPlantName.text = item.nama
            binding.tvPlantPrice.text = item.harga
            binding.imgPlantPhoto.setImageResource(item.foto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemRowAtHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
package com.example.bootani

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bootani.RekomendasiFragment
import com.example.bootani.data.RekomendasiItem
import com.example.bootani.databinding.ItewRowRekomendasiBinding

class RekomendasiAdapter(private val items: List<RekomendasiItem>) :
    RecyclerView.Adapter<RekomendasiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItewRowRekomendasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.imgPlantPhoto.setImageResource(item.foto)
        holder.binding.tvPlantName.text = item.nama
        holder.binding.tvPlantPrice.text = item.harga
        holder.binding.tvPriceChange.text = item.persentase
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ItewRowRekomendasiBinding) : RecyclerView.ViewHolder(binding.root)
}


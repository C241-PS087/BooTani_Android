package com.example.bootani

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bootani.databinding.ItemRowTinjauHargaBinding

class TinjauHargaAdapter(private val itemList: List<TinjauHargaItem>) : RecyclerView.Adapter<TinjauHargaAdapter.TinjauHargaViewHolder>() {

    class TinjauHargaViewHolder(private val binding: ItemRowTinjauHargaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TinjauHargaItem) {
            binding.imgPlantPhoto.setImageResource(item.photoResId)
            binding.tvPlantName.text = item.name
            binding.tvPlantPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinjauHargaViewHolder {
        val binding = ItemRowTinjauHargaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TinjauHargaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TinjauHargaViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}

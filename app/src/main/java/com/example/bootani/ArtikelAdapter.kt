package com.example.bootani

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bootani.data.ResponseArtikelItem
import com.example.bootani.databinding.ItemRowArtikelBinding
import com.squareup.picasso.Picasso

class ArtikelAdapter(private val layoutInflater: LayoutInflater, private val listArtikel: List<ResponseArtikelItem>) : RecyclerView.Adapter<ArtikelAdapter.ArtikelViewHolder>() {
    class ArtikelViewHolder(val binding: ItemRowArtikelBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtikelViewHolder {
        val binding = ItemRowArtikelBinding.inflate(layoutInflater, parent, false)
        return ArtikelViewHolder(binding)
    }

    override fun getItemCount(): Int = listArtikel.size


    override fun onBindViewHolder(holder: ArtikelViewHolder, position: Int) {
        val artikel = listArtikel[position]
        Log.d("ArtikelAdapter", "onBindViewHolder: ${artikel.tagline}")
        Picasso.get().load(artikel.imageUrl).into(holder.binding.imgItemPhoto)
        holder.binding.tvItemName.text = artikel.tagline
        holder.binding.tvItemDescription.text = artikel.content
    }
}
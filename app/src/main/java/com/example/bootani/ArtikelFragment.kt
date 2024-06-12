package com.example.bootani

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ArtikelFragment : Fragment() {

    private lateinit var rvArtikel: RecyclerView
    private val list = ArrayList<Artikel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artikel, container, false)

        // Initialize RecyclerView
        rvArtikel = view.findViewById(R.id.rv_artikels)
        rvArtikel.setHasFixedSize(true)

        // Populate the list and set up the RecyclerView
        list.addAll(getListArtikels())
        showRecyclerList()

        return view
    }

    private fun getListArtikels(): ArrayList<Artikel> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listArtikel = ArrayList<Artikel>()
        for (i in dataName.indices) {
            val artikel = Artikel(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArtikel.add(artikel)
        }
        dataPhoto.recycle() // Ensure resources are properly recycled
        return listArtikel
    }

    private fun showRecyclerList() {
        rvArtikel.layoutManager = LinearLayoutManager(requireContext())
        val listArtikelAdapter = ListArtikelAdapter(list)
        rvArtikel.adapter = listArtikelAdapter
    }
}

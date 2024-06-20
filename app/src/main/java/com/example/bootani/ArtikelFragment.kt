package com.example.bootani

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootani.data.UserPreferences
import com.example.bootani.viewModel.ArtikelViewModel
import com.example.bootani.viewModel.UserViewModel
import com.example.bootani.viewModel.ViewModelFactory

class ArtikelFragment : Fragment() {

    private lateinit var rvArtikel: RecyclerView
    private lateinit var artikelViewModel: ArtikelViewModel

    private val preferences by lazy { UserPreferences.getInstance(requireContext().dataStore) }
    private val userViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(preferences))[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_artikel, container, false)

        // Initialize RecyclerView
        rvArtikel = view.findViewById(R.id.rv_artikels)
        rvArtikel.setHasFixedSize(true)

        // Initialize ViewModel
        artikelViewModel = ViewModelProvider(this).get(ArtikelViewModel::class.java)

        // Get articles for a given username
        userViewModel.getUserName().observe(viewLifecycleOwner) { username ->
            artikelViewModel.getArtikels(username)
        }
        // Observe the LiveData from the ViewModel
        artikelViewModel.getArtikelsLiveData()
            .observe(viewLifecycleOwner, Observer { responseArtikel ->
                if (responseArtikel != null) {
                    Log.d("ArtikelFragment", "onChanged: ${responseArtikel.size}")
                    rvArtikel.layoutManager = LinearLayoutManager(requireContext())
                    rvArtikel.adapter = ArtikelAdapter(LayoutInflater.from(requireContext()), responseArtikel)
                }
            })

        return view
    }
}
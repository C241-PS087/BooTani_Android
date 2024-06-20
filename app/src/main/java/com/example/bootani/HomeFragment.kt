package com.example.bootani

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bootani.data.HomeItem
import com.example.bootani.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val names = resources.getStringArray(R.array.data_name)
        val prices = resources.getStringArray(R.array.data_price)
        val photos = resources.obtainTypedArray(R.array.data_photo)

        val items = names.indices.map { i ->
            HomeItem(
                foto = photos.getResourceId(i, 0),
                nama = names[i],
                harga = prices[i]
            )
        }

        // Sort the items by price in descending order and take the first two items
        val sortedItems = items.sortedByDescending { it.harga.replace("Rp. ", "").replace(",", "").toInt() }.take(2)

        val adapter = HomeAdapter(sortedItems)
        binding.rvBest2.layoutManager = LinearLayoutManager(context)
        binding.rvBest2.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
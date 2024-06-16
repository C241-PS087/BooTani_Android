package com.example.bootani

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bootani.databinding.FragmentTinjauBinding

class TinjauHargaFragment : Fragment() {

    private var _binding: FragmentTinjauBinding? = null
    private val binding get() = _binding!!

    private lateinit var tinjauHargaAdapter: TinjauHargaAdapter
    private lateinit var itemList: List<TinjauHargaItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTinjauBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.rvFragmentTinjau.layoutManager = LinearLayoutManager(context)
        binding.rvFragmentTinjau.setHasFixedSize(true)

        itemList = getItemList()
        tinjauHargaAdapter = TinjauHargaAdapter(itemList)
        binding.rvFragmentTinjau.adapter = tinjauHargaAdapter

        return view
    }

    private fun getItemList(): List<TinjauHargaItem> {
        val names = resources.getStringArray(R.array.data_name)
        val prices = resources.getStringArray(R.array.data_price)
        val photos = resources.obtainTypedArray(R.array.data_photo)
        val items = mutableListOf<TinjauHargaItem>()
        for (i in names.indices) {
            val photo = photos.getResourceId(i, -1)
            items.add(TinjauHargaItem(photo, names[i], prices[i]))
        }
        photos.recycle()
        return items
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
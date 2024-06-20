package com.example.bootani

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bootani.data.RekomendasiItem
import com.example.bootani.databinding.FragmentRekomendasiBinding

class RekomendasiFragment : Fragment() {

    private var _binding: FragmentRekomendasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRekomendasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val items = arguments?.getParcelableArrayList<RekomendasiItem>(ARG_ITEMS) ?: emptyList()

    binding.rvTop2.layoutManager = LinearLayoutManager(context)
    binding.rvTop2.adapter = RekomendasiAdapter(items.take(2))

    binding.rvTransaksi.layoutManager = LinearLayoutManager(context)
    binding.rvTransaksi.adapter = RekomendasiAdapter(items)
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_ITEMS = "items"
        fun newInstance(items: List<RekomendasiItem>): RekomendasiFragment {
            val fragment = RekomendasiFragment()
            val args = Bundle()
            args.putParcelableArrayList(ARG_ITEMS, ArrayList(items))
            fragment.arguments = args
            return fragment
        }
    }
}
package com.example.bootani

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bootani.data.HomeItem
import com.example.bootani.data.UserPreferences
import com.example.bootani.databinding.FragmentHomeBinding
import com.example.bootani.viewModel.UserViewModel
import com.example.bootani.viewModel.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val preferences by lazy { UserPreferences.getInstance(requireContext().dataStore) }
    private val userViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(preferences))[UserViewModel::class.java]
    }

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
        val sortedItems =
            items.sortedByDescending { it.harga.replace("Rp. ", "").replace(",", "").toInt() }
                .take(2)

        val adapter = HomeAdapter(sortedItems)
        binding.rvBest2.layoutManager = LinearLayoutManager(context)
        binding.rvBest2.adapter = adapter

        userViewModel.getUserName().observe(viewLifecycleOwner) {
            binding.tvUserName.text = it
        }
        userViewModel.getEmail().observe(viewLifecycleOwner) {
            binding.tvEmail.text = it
        }
        userViewModel.getUserName().observe(viewLifecycleOwner) { username ->
            if (username.isNotEmpty()) {
                binding.profileImage.text = username.first().uppercase().toString()
            }

        }

        binding.tvlogout.setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle("Logout")
                setMessage("Are you sure you want to logout?")
                setPositiveButton("Yes") { dialog, _ ->
                    // intent to welcome activity
                    Intent(context, WelcomeActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                        userViewModel.clearDataLogin()
                    }

                    dialog.dismiss()
                }
                setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
            }.create().show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.bootani

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.bootani.data.RekomendasiItem
import com.example.bootani.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)  // Ensure this layout file contains the required views


        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottom_home -> openFragment(HomeFragment())
                R.id.bottom_tinjau_harga -> openFragment(TinjauHargaFragment())
                R.id.bottom_recommend -> {
                    val names = resources.getStringArray(R.array.data_name)
                    val prices = resources.getStringArray(R.array.data_price)
                    val percentages = resources.getStringArray(R.array.price_increase_percentage)
                    val photos = resources.obtainTypedArray(R.array.data_photo)

                    val items = names.indices.map { i ->
                        RekomendasiItem(
                            foto = photos.getResourceId(i, 0),
                            nama = names[i],
                            harga = prices[i],
                            persentase = percentages[i]
                        )
                    }

                    val sortedItems = items.sortedByDescending { it.harga }
                    openFragment(RekomendasiFragment.newInstance(sortedItems))
                }
                R.id.bottom_artikel -> openFragment(ArtikelFragment())
            }
            true
        }
        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home -> openFragment(HomeFragment())
            R.id.nav_tinjau_harga -> openFragment(TinjauHargaFragment())
            R.id.nav_rekomendasi_tanaman -> openFragment(RekomendasiFragment())
            R.id.nav_artikel -> openFragment(ArtikelFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}

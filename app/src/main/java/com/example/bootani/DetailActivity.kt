package com.example.bootani

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var tvDescription: TextView
    private lateinit var ivPhoto: ImageView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvName = findViewById(R.id.tv_item_name)
        tvDescription = findViewById(R.id.tv_item_description)
        ivPhoto = findViewById(R.id.img_item_photo)

        val dataArtikel = intent.getParcelableExtra<Artikel>("key_artikel")

            tvName.text = dataArtikel?.name
            tvDescription.text = dataArtikel?.description
            Picasso.get().load(dataArtikel?.photoUrl).into(ivPhoto)
    }
}

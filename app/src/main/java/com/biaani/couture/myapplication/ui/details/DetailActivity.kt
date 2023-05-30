package com.biaani.couture.myapplication.ui.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.biaani.couture.myapplication.databinding.ActivityDetailBinding
import com.biaani.couture.myapplication.ui.main.MainActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var title: String
    private lateinit var thumbnail: String
    private lateinit var description: String
    private var price: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initConfig()
    }

    private fun initConfig() {
        title = intent.getStringExtra("title").toString()
        thumbnail = intent.getStringExtra("img").toString()
        description = intent.getStringExtra("description").toString()
        price = intent.getIntExtra("price", 0)


        Picasso.with(this)
            .load(thumbnail)
            .fit()
            .centerCrop()
            .into(binding.ivThumbnail)

        binding.tvDescription.text = description
        binding.tvPrice.text = price.toString()
        binding.tvTitle.text = title

        binding.btnClose.setOnClickListener(close)

    }

    private val close = View.OnClickListener {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
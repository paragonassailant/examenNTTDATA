package com.biaani.couture.myapplication.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.biaani.couture.myapplication.R
import com.biaani.couture.myapplication.data.entities.Products
import com.biaani.couture.myapplication.databinding.ActivityMainBinding
import com.biaani.couture.myapplication.ui.details.DetailActivity
import com.biaani.couture.myapplication.ui.main.adapter.ProductAdapter
import com.biaani.couture.myapplication.ui.main.auxilar.IAuxiliarProducts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IAuxiliarProducts {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var gridLayoutManager: LinearLayoutManager
    private lateinit var productAdapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.request()
        initData()
        setObservers()
    }

    private fun initData() {

    }

    private fun setObservers() {
        viewModel.onSuccess.observe(this) {
            loadRV(it)
        }
        viewModel.onError.observe(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            Log.d("ERROR", it.message)
        }
    }

    private fun loadRV(product: List<Products>) {
        productAdapter = ProductAdapter(product, this)
        gridLayoutManager = LinearLayoutManager(this)

        binding.rvProduct.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = productAdapter
        }
    }

    override fun selectItem(products: Products) {
        val i = Intent(this, DetailActivity::class.java)
        i.putExtra("title", products.title)
        i.putExtra("img", products.thumbnail)
        i.putExtra("description", products.description)
        i.putExtra("price", products.price)
        openActivity(i)
    }

    private fun openActivity(i: Intent) {
        startActivity(i)
        finish()
    }
}
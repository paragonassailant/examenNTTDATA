package com.biaani.couture.myapplication.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biaani.couture.myapplication.R
import com.biaani.couture.myapplication.data.entities.Products
import com.biaani.couture.myapplication.databinding.ItemProductsListBinding
import com.biaani.couture.myapplication.ui.main.auxilar.IAuxiliarProducts

class ProductAdapter(private var products: List<Products>, private var listener:IAuxiliarProducts) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var ctx: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ctx = parent.context
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_products_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = products[position]
        holder.binding.tvName.text = item.title
        holder.binding.tvPrice.text = item.price.toString()
        holder.binding.tvDescription.text = item.description

        holder.binding.cv.setOnClickListener {
            listener.selectItem(item)
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemProductsListBinding.bind(view)
    }
}
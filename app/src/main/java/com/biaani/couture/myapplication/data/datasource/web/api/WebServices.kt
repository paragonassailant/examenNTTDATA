package com.biaani.couture.myapplication.data.datasource.web.api

import com.biaani.couture.myapplication.data.datasource.web.response.OnProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    companion object {
        private const val products = "products"
    }


    @GET("$products")
    fun getProducts(): Call<OnProductResponse>
}
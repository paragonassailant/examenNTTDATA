package com.biaani.couture.myapplication.data.datasource.web.response

import com.biaani.couture.myapplication.data.entities.Products
import com.google.gson.annotations.SerializedName

data class OnProductResponse(
    @SerializedName("products") val result: List<Products>,
    @SerializedName("total") val total:Int,
    @SerializedName("skip") val skip:Int,
    @SerializedName("limit") val limit:Int
)

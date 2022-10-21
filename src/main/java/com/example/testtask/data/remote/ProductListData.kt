package com.example.testtask.data.remote

import com.google.gson.annotations.SerializedName

class ProductListData {
    @SerializedName("products")
    val pizzas: ArrayList<ProductData>? = null
}
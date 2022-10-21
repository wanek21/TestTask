package com.example.testtask.data.remote

import com.google.gson.annotations.SerializedName

data class ProductData(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("price")
    val price: Int?
)

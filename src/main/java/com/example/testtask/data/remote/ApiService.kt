package com.example.testtask.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/products/")
    suspend fun getPizzas(): Response<ProductListData>
}
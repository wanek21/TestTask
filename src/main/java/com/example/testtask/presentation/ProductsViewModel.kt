package com.example.testtask.presentation

import androidx.lifecycle.ViewModel
import com.example.testtask.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    fun getPizzas() = getProductsUseCase.execute()
}
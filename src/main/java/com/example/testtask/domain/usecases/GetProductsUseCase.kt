package com.example.testtask.domain.usecases

import androidx.lifecycle.asLiveData
import com.example.testtask.data.remote.ProductData
import com.example.testtask.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : UseCase<ArrayList<ProductData>> {

    override fun execute() = productRepository.getProducts().asLiveData()
}
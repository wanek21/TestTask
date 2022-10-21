package com.example.testtask.domain.repositories

import com.example.testtask.data.local.Product
import com.example.testtask.data.local.ProductDao
import com.example.testtask.data.remote.ApiService
import com.example.testtask.data.remote.ProductData
import com.example.testtask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productsDao: ProductDao
) {

    // получаем список меню (пиццы)
    fun getProducts() = flow {
        emit(Resource.loading(null))

        // пытаемся получить продукты с интернета
        val response = apiService.getPizzas()
        if(response.isSuccessful) {
            emit(Resource.success(response.body()?.pizzas))
            val products = Resource.success(response.body())

            // сохраняем продукты на устройстве
            productsDao.insertAll(toProductListDao(products.data?.pizzas))
        } else { // если запрос неуспешный, пытаемся получить сохраненые продукты
            val productsDaoList = productsDao.getAll() as ArrayList
            if(productsDaoList.isEmpty()) {
                emit(Resource.error("", null))
            } else {
                emit(Resource.error("", toProductListDomain(productsDaoList)))
            }
        }
    }.catch { // при исключениях передаем оффлайн данные
        val productsDaoList = productsDao.getAll() as ArrayList
        if(productsDaoList.isEmpty()) {
            emit(Resource.error("", null))
        } else {
            emit(Resource.error("", toProductListDomain(productsDaoList)))
        }
    }.flowOn(Dispatchers.IO)

    // модель домена в модель бд
    private fun toProductListDao(productLis: ArrayList<ProductData>?): ArrayList<Product> {
        val productsDaoList = productLis?.map {
            Product(null, it.title, it.description, it.price)
        } as ArrayList
        return productsDaoList
    }

    // модель бд в модель домена
    private fun toProductListDomain(productDaoList: ArrayList<Product>): ArrayList<ProductData> {
        val productsList = productDaoList.map {
            ProductData(null, it.title, it.description, it.price)
        } as ArrayList
        return productsList
    }
}
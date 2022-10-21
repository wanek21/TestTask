package com.example.testtask.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testtask.data.local.ProductDao
import com.example.testtask.data.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocaleData {

    @Provides
    fun getRoomDatabase(@ApplicationContext applicationContext: Context): ProductDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ProductDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun getProductsDao(productDatabase: ProductDatabase): ProductDao {
        return productDatabase.getProducts()
    }
}
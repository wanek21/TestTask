package com.example.testtask.domain.usecases

import androidx.lifecycle.LiveData
import com.example.testtask.utils.Resource

interface UseCase<R> {
    fun execute(): LiveData<Resource<R>>
}
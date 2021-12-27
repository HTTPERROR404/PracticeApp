package com.example.pocpracticeapp.data.repository

import com.example.pocpracticeapp.data.remote.ApiService
import com.example.pocpracticeapp.domain.model.ListingsRoot
import com.example.pocpracticeapp.domain.repository.ListingsRepository
import javax.inject.Inject

class ListingsRepositoryImp @Inject constructor(private val apiService: ApiService) : ListingsRepository {

    override suspend fun getPosts(): ListingsRoot {
        return apiService.getListings()
    }
}
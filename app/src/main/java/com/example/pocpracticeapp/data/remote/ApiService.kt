package com.example.pocpracticeapp.data.remote

import com.example.pocpracticeapp.domain.model.ListingsRoot
import retrofit2.http.GET

interface ApiService {

    @GET("default/dynamodb-writer")
    suspend fun getListings(): ListingsRoot
}
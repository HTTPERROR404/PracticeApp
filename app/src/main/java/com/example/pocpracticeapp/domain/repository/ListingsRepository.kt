package com.example.pocpracticeapp.domain.repository

import com.example.pocpracticeapp.domain.model.ListingsRoot

interface ListingsRepository {

    suspend fun getPosts(): ListingsRoot
}
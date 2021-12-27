package com.example.pocpracticeapp.domain.usecase.base

import com.example.pocpracticeapp.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}


package com.example.pocpracticeapp.domain.usecase

import com.example.pocpracticeapp.domain.model.ListingsRoot
import com.example.pocpracticeapp.domain.repository.ListingsRepository
import com.example.pocpracticeapp.domain.usecase.base.UseCase
import javax.inject.Inject


class GetListingsUseCase @Inject constructor(
    private val listingsRepository: ListingsRepository
) : UseCase<ListingsRoot, Any?>() {

    override suspend fun run(params: Any?): ListingsRoot {
        return listingsRepository.getPosts()
    }

}
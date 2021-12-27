package com.example.pocpracticeapp.domain.model

import com.google.gson.annotations.SerializedName

data class ListingsRoot(@SerializedName("results")
                        val results: List<ResultsItem>?)
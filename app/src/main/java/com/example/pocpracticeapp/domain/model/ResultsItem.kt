package com.example.pocpracticeapp.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "listings")
data class ResultsItem(@PrimaryKey
                       @SerializedName("uid")
                       val uid: String = "",
                       @SerializedName("price")
                       val price: String = "",
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("created_at")
                       val createdAt: String = "",
                       @Ignore
                       @SerializedName("image_ids")
                       val imageIds: List<String>?,
                       @Ignore
                       @SerializedName("image_urls")
                       val imageUrls: List<String>?,
                       @Ignore
                       @SerializedName("image_urls_thumbnails")
                       val imageUrlsThumbnails: List<String>?){
    constructor(uid: String, price: String, name: String, createdAt: String) : this(uid, price, name, createdAt, null, null, null)
}
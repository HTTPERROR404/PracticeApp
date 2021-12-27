package com.example.imageloader.cache

import android.graphics.Bitmap

interface ImageCache {
    fun put(url: String, bitmap: Bitmap)
    fun get(url: String): Bitmap?
    fun clear()
}
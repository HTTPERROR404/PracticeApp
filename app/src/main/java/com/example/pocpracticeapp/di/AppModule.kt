package com.example.pocpracticeapp.di

import android.content.Context
import com.example.imageloader.ImageLoader
import com.example.pocpracticeapp.data.remote.ApiService
import com.example.pocpracticeapp.data.repository.ListingsRepositoryImp
import com.example.pocpracticeapp.domain.repository.ListingsRepository
import com.example.pocpracticeapp.domain.usecase.GetListingsUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TIME_OUT = 30L

    @Singleton
    @Provides
    fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitListingInstance(okHttpClient: OkHttpClient,gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://ey3f2y0nre.execute-api.us-east-1.amazonaws.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideListingService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun createListingsRepository(apiService: ApiService): ListingsRepository {
        return ListingsRepositoryImp(apiService)
    }

    @Provides
    fun createGetListingsUseCase(postsRepository: ListingsRepository): GetListingsUseCase {
        return GetListingsUseCase(postsRepository)
    }

    @Provides
    @Singleton
    fun createImageLoader(): ImageLoader {
        return ImageLoader()
    }
}
package com.yunshen.yunapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

object Api{
    private const val IMAGE_URL = "https://xin.xingvvhuang.cn"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS) // 连接超时
        .readTimeout(10, TimeUnit.SECONDS)    // 读取超时
        .writeTimeout(10, TimeUnit.SECONDS)   // 写入超时
        .build()

    private val imageRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(IMAGE_URL)
        .client(okHttpClient)
        .build()

    val imageService : ImageService by lazy {
        imageRetrofit.create(ImageService::class.java)
    }


}

interface ImageService{
    @GET("api.php?img")
    suspend fun getAnimeList(): ImageData
}
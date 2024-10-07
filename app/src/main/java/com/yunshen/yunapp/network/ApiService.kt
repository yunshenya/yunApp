package com.yunshen.yunapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Api{
    private const val IMAGE_URL = "https://api.btstu.cn"
    private val imageRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(IMAGE_URL)
        .build()

    val imageService : ImageService by lazy {
        imageRetrofit.create(ImageService::class.java)
    }


}

interface ImageService{
    @GET("sjbz/api.php")
    suspend fun getAnimeList(@Query("lx") type: String = "dongman", @Query("format") format: String = "json"): ImageData
}
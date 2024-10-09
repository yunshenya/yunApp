package com.yunshen.yunapp.network

import kotlinx.serialization.Serializable

@Serializable
data class ImageData(
    val code: Int,
    val data: Data
){
    @Serializable
    data class Data(
        val imgurl: String,
        val width: Int,
        val height: Int)
}
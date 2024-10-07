package com.yunshen.yunapp.network

import kotlinx.serialization.Serializable

@Serializable
data class ImageData(
    val code: Int,
    val imgurl: String,
    val width: Int,
    val height: Int
)
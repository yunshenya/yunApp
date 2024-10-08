package com.yunshen.yunapp.viewmodel

import com.yunshen.yunapp.network.ImageData

data class UIState(
    val imageList: List<ImageData> = emptyList(),
    val image: String = "",
    val carousel: List<String> = listOf(
        "https://img.btstu.cn/api/images/5b0fafb929d12.jpg",
        "https://img.btstu.cn/api/images/5dfc964e4c6d7.jpg",
        "https://img.btstu.cn/api/images/5e782a1c0469d.jpg"
    )
)

data class DetailState(
    val title: String,
    val content: String
)
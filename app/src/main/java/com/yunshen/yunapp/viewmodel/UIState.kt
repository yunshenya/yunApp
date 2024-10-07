package com.yunshen.yunapp.viewmodel

import com.yunshen.yunapp.network.ImageData

data class UIState(
    val imageList: List<ImageData> = emptyList(),
    val image: String = "",
)

data class DetailState(
    val title: String,
    val content: String
)
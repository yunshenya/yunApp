package com.yunshen.yunapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunshen.yunapp.network.Api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class UIViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()
    private val counter = mutableIntStateOf(0)
    val count = counter


    val images = listOf(
        "https://img.btstu.cn/api/images/5b0fafb929d12.jpg",
        "https://img.btstu.cn/api/images/5dfc964e4c6d7.jpg",
        "https://img.btstu.cn/api/images/5e782a1c0469d.jpg"
    )

    init {
        getAnimeList()
        getImageList()
    }

    private fun getAnimeList(){
        viewModelScope.launch{
            try {
                val result = Api.imageService.getAnimeList()
                Log.d("getAnimeList", "getAnimeList: ${result.imgurl}")
                _uiState.update {
                    it.copy(image = result.imgurl)
                }
        } catch (e: Exception){
            Log.d("getAnimeList", "getAnimeList: ${e.message}")
        }
        }
    }


    private fun getImageList(){
        viewModelScope.launch{
            val newImages = (1..10).map {
                Api.imageService.getAnimeList()
            }
            _uiState.update {
                it.copy(imageList = newImages)
            }
        }
    }

    fun add(){
        counter.intValue++
    }
}
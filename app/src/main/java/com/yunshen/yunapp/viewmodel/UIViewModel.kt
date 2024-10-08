package com.yunshen.yunapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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

    private var _isChecked = mutableStateOf(false) //因为传递参数使用不能使用by关键字，所以使用mutableStateOf
    val isChecked = _isChecked

    val dataList = listOf(
        DetailState("探索无限乐趣", "这里是游戏、动漫爱好者的天堂，也是创意与灵感碰撞的乐园。无论你是热衷于最新游戏资讯的玩家，还是寻找精美周边的收藏家，亦或是对动漫世界充满无限遐想的粉丝，这里都能满足你的需求。"),
        DetailState("分享与交流", "我们的论坛是连接志同道合朋友的桥梁。在这里，你可以发布自己的心得体验，参与各种话题讨论，与来自世界各地的同好们共同成长。无论是攻略心得还是作品分析，每一次交流都将让你收获满满。"),
        DetailState("赞助与支持", "为了给用户带来更好的体验和服务，我们诚邀各路商家和品牌加入我们的赞助行列。通过合作，不仅能够提升您的品牌知名度，还能帮助我们打造一个更加丰富多彩的社区环境。让我们携手共进，共创美好未来！"),
        DetailState("特色活动", "每月定期举办线上线下结合的活动，包括但不限于游戏挑战赛、动漫角色扮演（Cosplay）大赛、以及各类创作比赛等。参与其中，不仅能赢取丰厚奖励，还有机会结识更多有趣的人。"),
        DetailState("安全与信任", "我们致力于营造一个健康、积极的网络环境。所有交易均在安全可控的前提下进行，确保每一位用户的权益得到保障。"),
        DetailState("加入我们", "加入我们，开启一段奇妙旅程吧！无论是寻找伙伴还是展现自我，这里都是你最好的选择。立即注册成为会员，开启你的精彩生活！"),
    )

    init {
        getCarouselList()
        getAnimeList()
        getImageList()
    }

    private fun getAnimeList(){
        viewModelScope.launch{
            try {
                val result = Api.imageService.getAnimeList()
                _uiState.update {
                    it.copy(image = result.imgurl)
                }
        } catch (e: Exception){
            Log.d("getAnimeList", "getAnimeList: ${e.message}")
        }
        }
    }

   fun getCarouselList(){
        viewModelScope.launch{
            try {
                val newImages = (1..7).map {
                    Api.imageService.getAnimeList()
                }
                _uiState.update {
                    it.copy(carousel = newImages.map { it.imgurl })
                }
        }catch (e: Exception){
            Log.d("getAnimeList", "getAnimeList: ${e.message}")
            }
        }
    }


    private fun getImageList(){
        viewModelScope.launch{
            getImageListAction()
        }
    }

    fun add(){
        counter.intValue++
    }

    suspend fun refresh(){
        getImageListAction()
    }

    private suspend fun getImageListAction(){
        val newImages = (1..6).map {
            Api.imageService.getAnimeList()
        }
        _uiState.update {
            it.copy(imageList = newImages)
        }
    }

    fun updateChecked(checked: Boolean){
        _isChecked.value = checked
    }
}
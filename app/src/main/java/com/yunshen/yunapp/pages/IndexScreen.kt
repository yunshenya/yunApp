package com.yunshen.yunapp.pages

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.yunshen.yunapp.R
import com.yunshen.yunapp.navigation.NavTopBar
import com.yunshen.yunapp.viewmodel.UIViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndexScreen(modifier: Modifier = Modifier, viewModel: UIViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val refreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = modifier.fillMaxSize(),
        isRefreshing = isRefreshing,
        state = refreshState,
        onRefresh = {
            scope.launch {
                isRefreshing = true
                viewModel.refresh()
                isRefreshing = false
            }
        }) {
        AsyncImage(
            model = state.image,
            error = painterResource(id = R.drawable.hhead),
            contentDescription = "背景图",
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp)
                .alpha(10f),
            contentScale = ContentScale.Crop
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                NavTopBar {
                    Text(
                        text = "梦想之门", color = Color(0xFF6A0DAD),
                        fontWeight = FontWeight.Black, fontSize = 24.sp
                    )
                }
            }
            item {
                Column(modifier = Modifier.fillMaxSize()) {
                    val images = viewModel.images
                    val pagerState = rememberPagerState(pageCount = { images.size })
                    val coroutineScope = rememberCoroutineScope()
                    LaunchedEffect(pagerState.settledPage) {
                        delay(1000)
                        val nextPage = (pagerState.settledPage + 1) % images.size
                        pagerState.animateScrollToPage(nextPage)
                    }
                    HorizontalPager(
                        state = pagerState,
                        contentPadding = PaddingValues(10.dp)
                    ) { index ->
                        val imgScale by animateFloatAsState(
                            targetValue = if (pagerState.currentPage == index) 1f else 0.8f,
                            label = "缩放动画", animationSpec = tween(300)
                        )
                        AsyncImage(
                            model = images[index],
                            error = painterResource(id = R.drawable.hhead),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(10.dp)
                                )
                                .scale(imgScale),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(20.dp)
                            .align(Alignment.CenterHorizontally),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        images.indices.forEach { radioIndex ->
                            RadioButton(
                                modifier = Modifier.scale(0.5f),
                                selected = radioIndex == pagerState.currentPage,
                                onClick = {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(radioIndex)
                                    }
                                })
                        }
                    }
                }
            }

            items(state.imageList.size) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .width(400.dp)
                            .padding(16.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp) // 设置阴影
                    ) {
                        AsyncImage(
                            model = state.imageList[it].imgurl,
                            error = painterResource(id = R.drawable.hhead),
                            contentDescription = "Anime girl on couch",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                            contentScale = ContentScale.Crop
                        )
                        // 关键：去除固定高度，让 Column 根据内容自适应
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "探索无尽的乐趣",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "这里是游戏、动漫爱好者的天堂，也是创意与灵感碰撞的乐园。无论你是热衷于最新游戏资讯的玩家，还是寻找美周边的收藏家，亦或是对动漫世界充满无限遐想的粉丝，这里都能满足你的需求。",
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            val context = LocalContext.current
                            Button(
                                onClick = {
                                    Toast.makeText(context, "还在开发中", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("进入")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IndexPrivate() {
    IndexScreen()
}
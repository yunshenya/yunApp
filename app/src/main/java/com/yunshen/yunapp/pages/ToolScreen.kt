package com.yunshen.yunapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunshen.yunapp.R
import com.yunshen.yunapp.viewmodel.StoreManager
import com.yunshen.yunapp.viewmodel.UIViewModel

@Composable
fun ToolsScreen(
    modifier: Modifier = Modifier,
    viewModel: UIViewModel = viewModel(),
    storeManager: StoreManager = StoreManager(LocalContext.current)
) {
    val checked = storeManager.checked.collectAsState(initial = false)
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(4){
                Card(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = {
                        viewModel.count.intValue++
                    })) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (checked.value){
                        Image(modifier = Modifier.fillMaxSize()
                            .blur(10.dp)
                            .alpha(10f),
                            painter = painterResource(id = R.drawable.hhead),
                            contentScale = ContentScale.Crop,
                            contentDescription = null
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center){
                        Text(text = "工具$it", fontSize = 16.sp)
                    }
                }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ToolsPrivate() {
    ToolsScreen()
}
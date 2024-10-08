package com.yunshen.yunapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun NavTopBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val translucentWhite = Color(0x90FFFFFF) // 0x90 表示 57.6% 的透明度
    Row(
        modifier = modifier
            .background(translucentWhite)
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        content()
    }
}


@Composable
fun NavBottomBar(
    bottomBarItemSelected: String,
    onclick: (Destination) -> Unit
) {
    NavigationBar {
        BOTTOM_NAVIGATION_ITEMS.forEach {
            NavigationBarItem(
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = bottomBarItemSelected == it.id,
                label = { Text(text = it.text) },
                onClick = {
                    onclick(it)
                }
            )
        }
    }
}
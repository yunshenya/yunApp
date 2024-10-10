package com.yunshen.yunapp.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavTopBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    val translucentWhite = Color(0x90FFFFFF)
    TopAppBar(
        title = {
            content()
        },
        modifier = modifier
            .background(translucentWhite)
            .fillMaxWidth()
    )
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
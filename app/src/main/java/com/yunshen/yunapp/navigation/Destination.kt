package com.yunshen.yunapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

data class Destination(
    val icon: ImageVector,
    val id: Destinations,
    val selectedIcon: ImageVector,
    val text: String
)


enum class Destinations{
    INDEX,
    TOOLS,
    LOGIN,
    SETTING
}


val BOTTOM_NAVIGATION_ITEMS: List<Destination> = listOf(
    Destination(
        icon = Icons.Filled.Home,
        id = Destinations.INDEX,
        selectedIcon = Icons.Filled.Home,
        text = "首页"),

    Destination(
        icon = Icons.Outlined.Build,
        id = Destinations.TOOLS,
        selectedIcon = Icons.Filled.Home,
        text = "工具"
    ),

    Destination(
        icon = Icons.Filled.Settings,
        id = Destinations.SETTING,
        selectedIcon = Icons.Filled.Home,
        text = "设置")
)


class AppNavigationActions(private val navController: NavHostController){
    fun navigateTo(destination: Destinations){
        navController.navigate(destination.name){
            popUpTo(navController.graph.startDestinationId){
                saveState = true
            }
            launchSingleTop = true
        }
    }
}
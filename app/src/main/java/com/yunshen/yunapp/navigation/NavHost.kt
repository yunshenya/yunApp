package com.yunshen.yunapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yunshen.yunapp.pages.IndexScreen
import com.yunshen.yunapp.pages.LoginScreen
import com.yunshen.yunapp.pages.SettingScreen
import com.yunshen.yunapp.pages.ToolsScreen
import com.yunshen.yunapp.viewmodel.StoreManager
import com.yunshen.yunapp.viewmodel.UIViewModel

@Composable
fun Navigation(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewmodel: UIViewModel,
    storeManager: StoreManager
) {
    NavHost(navController = navHostController,
        startDestination = Destinations.INDEX.name,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
        }) {
        composable(route = Destinations.INDEX.name) {
            IndexScreen(modifier = modifier, viewModel = viewmodel)
        }

        composable(route = Destinations.LOGIN.name) {
            LoginScreen(modifier = modifier, viewModel = viewmodel)
        }

        composable(route = Destinations.TOOLS.name) {
            ToolsScreen(modifier = modifier, viewModel = viewmodel, storeManager = storeManager)
        }

        composable(route = Destinations.SETTING.name) {
            SettingScreen(modifier = modifier, storeManager = storeManager)
        }
    }
}
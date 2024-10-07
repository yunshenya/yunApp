package com.yunshen.yunapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yunshen.yunapp.pages.IndexScreen
import com.yunshen.yunapp.pages.LoginScreen
import com.yunshen.yunapp.pages.SettingScreen
import com.yunshen.yunapp.pages.ToolsScreen
import com.yunshen.yunapp.viewmodel.UIViewModel

@Composable
fun Navigation(navHostController: NavHostController, modifier: Modifier = Modifier) {
    //为什么不能使用val viewmodel =  remember { UIViewModel() },因为如果使用这个会导致屏幕反转就被重组了
    //所以应该使用val viewmodel: UIViewModel = viewModel()
    //viewModel不会导致这个重组
    val viewmodel: UIViewModel = viewModel()
    NavHost(navController = navHostController, startDestination = Destinations.INDEX.name){
        composable(route = Destinations.INDEX.name){
            IndexScreen(modifier=modifier, viewModel = viewmodel)
        }

        composable(route = Destinations.LOGIN.name){
            LoginScreen(modifier = modifier, viewModel = viewmodel)
        }

        composable(route= Destinations.TOOLS.name){
            ToolsScreen(modifier = modifier, viewModel = viewmodel)
        }

        composable(route = Destinations.SETTING.name){
            SettingScreen(modifier = modifier, viewModel = viewmodel)
        }
    }
}
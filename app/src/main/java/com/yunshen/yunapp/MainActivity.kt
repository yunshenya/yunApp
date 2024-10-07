package com.yunshen.yunapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yunshen.yunapp.navigation.AppNavigationActions
import com.yunshen.yunapp.navigation.Destinations
import com.yunshen.yunapp.navigation.NavBottomBar
import com.yunshen.yunapp.navigation.Navigation
import com.yunshen.yunapp.ui.theme.YunAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            //创建navigationActions
            //remember(navHostController)可以防止navHostController被重新创建
            val navigationActions = remember(navHostController) {
                AppNavigationActions(navHostController)  //创建navigationActions
            }
            //获取当前路由
            val currentBackStackState by navHostController.currentBackStackEntryAsState()
            //获取当前路由的route
            val currentDestination = currentBackStackState?.destination?.route ?: Destinations.INDEX.name
            YunAppTheme {
                Scaffold (bottomBar = {
                    NavBottomBar(
                        onclick = navigationActions::navigateTo,
                        bottomBarItemSelected = currentDestination
                    )
                }){ innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Navigation(modifier = Modifier.padding(innerPadding), navHostController = navHostController)
                    }
                }
            }
        }
    }
}
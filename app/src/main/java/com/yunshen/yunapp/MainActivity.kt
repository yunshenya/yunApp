package com.yunshen.yunapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.yunshen.yunapp.navigation.AppNavigationActions
import com.yunshen.yunapp.navigation.Destinations
import com.yunshen.yunapp.navigation.NavBottomBar
import com.yunshen.yunapp.navigation.NavTopBar
import com.yunshen.yunapp.navigation.Navigation
import com.yunshen.yunapp.ui.theme.YunAppTheme
import com.yunshen.yunapp.viewmodel.StoreManager
import com.yunshen.yunapp.viewmodel.UIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkStatusChecker { isNetworkAvailable ->
                if (isNetworkAvailable){
                    Online()
                }else{
                    Offline()
                }
            }
        }
    }
}



@Composable
fun Online() {
    val navHostController = rememberNavController()
    //创建navigationActions
    //remember(navHostController)可以防止navHostController被重新创建
    val navigationActions = remember(navHostController) {
        AppNavigationActions(navHostController)  //创建navigationActions
    }
    //获取当前路由
    val currentBackStackState by navHostController.currentBackStackEntryAsState()
    //获取当前路由的route
    val currentDestination =
        currentBackStackState?.destination?.route ?: Destinations.INDEX.name
    //数据持久化
    val storeManager = StoreManager(LocalContext.current)
    //为什么不能使用val viewmodel =  remember { UIViewModel() },因为如果使用这个会导致屏幕反转就被重组了
    //所以应该使用val viewmodel: UIViewModel = viewModel()
    //viewModel不会导致这个重组
    val viewmodel: UIViewModel = viewModel()
    YunAppTheme {
        Scaffold(
            topBar = {
                when (currentDestination) {
                    Destinations.INDEX.name -> {
                        NavTopBar {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "梦想之门", color = Color(0xFF6A0DAD),
                                    fontWeight = FontWeight.Black, fontSize = 24.sp
                                )
                            }
                        }
                    }
                }
            },
            bottomBar = {
                NavBottomBar(
                    onclick = navigationActions::navigateTo,
                    bottomBarItemSelected = currentDestination
                )
            },
            floatingActionButton = {
                when (currentDestination) {
                    Destinations.TOOLS.name -> FloatingActionButton(onClick = { viewmodel.count.intValue++ }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                    }
                }
            }
        ) { innerPadding ->
            Surface(modifier = Modifier.fillMaxSize()) {
                Navigation(
                    modifier = Modifier.padding(innerPadding),
                    navHostController = navHostController,
                    storeManager = storeManager,
                    viewmodel = viewmodel
                )
            }
        }
    }
}


@Composable
fun Offline() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "离线模式")
    }
}


@Composable
fun NetworkStatusChecker(content: @Composable (Boolean) -> Unit) {
    val context = LocalContext.current
    var isNetworkAvailable by remember { mutableStateOf(false) }

    DisposableEffect(context) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isNetworkAvailable = true
            }

            override fun onLost(network: Network) {
                isNetworkAvailable = false
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        onDispose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    content(isNetworkAvailable)
}
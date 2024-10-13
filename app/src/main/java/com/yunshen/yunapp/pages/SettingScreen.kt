package com.yunshen.yunapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yunshen.yunapp.R
import com.yunshen.yunapp.viewmodel.StoreManager
import kotlinx.coroutines.launch

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    storeManager: StoreManager = StoreManager(LocalContext.current)
) {
    val storeData = storeManager.checked.collectAsState(initial = false)
    val storeTheme = storeManager.theme.collectAsState(initial = false)
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.hhead),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .graphicsLayer {
                                shape = RoundedCornerShape(50)
                                clip = true
                            }
                            .size(50.dp),
                        contentDescription = null
                    )
                    Column {
                        Text(text = "用户名: 123456D")
                        Text(text = "邮箱: james.iredell@examplepetstore.com")
                    }
                }
            }
            SettingsCard(
                title = "标题",
                onSwitchChange = {
                    storeManager.updateChecked(it)
                },
                storeData = storeData
            )

            SettingsCard(
                title = "主题设置",
                onSwitchChange = {
                    storeManager.updateTheme(it)
                },
                storeData = storeTheme
            )
        }
    }
}


@Composable
fun SettingsCard(
    title: String,
    onSwitchChange: suspend (Boolean) -> Unit = {},
    storeData: State<Boolean>
) {
    val scope = rememberCoroutineScope()
    Card(
        modifier = Modifier.padding(top = 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.padding(10.dp))
            Switch(checked = storeData.value, onCheckedChange = {
                scope.launch {
                    onSwitchChange(it)
                }
            }, modifier = Modifier.padding(10.dp))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview() {
    SettingScreen()
}
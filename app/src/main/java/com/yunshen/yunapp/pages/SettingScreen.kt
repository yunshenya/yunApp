package com.yunshen.yunapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yunshen.yunapp.navigation.NavTopBar
import com.yunshen.yunapp.viewmodel.UIViewModel

@Composable
fun SettingScreen(modifier: Modifier = Modifier, viewModel: UIViewModel = viewModel()) {
    Box(modifier = modifier.fillMaxSize()) {
        Scaffold (
            topBar = {
                NavTopBar {
                    Text(text = "Setting")
                }
            }
        ){ innerPadding ->
            Column(
                modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (modifier = Modifier.fillMaxWidth().padding(innerPadding), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
                    Text(text = "设置")
                    Switch(checked = viewModel.isChecked.value, onCheckedChange = {
                        viewModel.updateChecked(it)
                    })
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SettingsPreview() {
    SettingScreen()
}
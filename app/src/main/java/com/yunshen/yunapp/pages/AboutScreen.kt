package com.yunshen.yunapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)){

        item{
            Text(text = "资源共享平台简介与api说明",
                modifier=Modifier.padding(top = 16.dp),
                fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        item{
            Column(verticalArrangement = Arrangement.SpaceBetween){
                Text(text = "一,平台简介",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AboutScreenPreview() {
    AboutScreen()
}
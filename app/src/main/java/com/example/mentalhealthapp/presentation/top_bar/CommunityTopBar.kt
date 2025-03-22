package com.example.mentalhealthapp.presentation.top_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommunityTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF9C27B0))
    ) {
        Column {
            Text(
                text = "App Settings",
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
                    .fillMaxWidth(),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Customize your experience",
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
        }

        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
        ) {
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.White
            )
        }
    }
}

package com.example.mentalhealthapp.presentation.top_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CommunityTopBar() {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF9C27B0))
//    ) {
//        Column {
//            Text(
//                text = "App Settings",
//                modifier = Modifier
//                    .padding(start = 16.dp, top = 16.dp, bottom = 4.dp)
//                    .fillMaxWidth(),
//                color = Color.White,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = "Customize your experience",
//                modifier = Modifier
//                    .padding(start = 16.dp, bottom = 16.dp)
//                    .fillMaxWidth(),
//                color = Color.White.copy(alpha = 0.7f),
//                fontSize = 14.sp
//            )
//        }
//
//        IconButton(
//            onClick = {},
//            modifier = Modifier
//                .align(Alignment.CenterEnd)
//                .padding(end = 8.dp)
//        ) {
//            Icon(
//                Icons.Default.Settings,
//                contentDescription = "Settings",
//                tint = Color.White
//            )
//        }
//    }
}
@Preview
@Composable
fun CommunityTopBarPreview() {
    CommunityTopBar()
}
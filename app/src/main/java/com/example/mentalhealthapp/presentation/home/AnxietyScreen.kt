package com.example.mentalhealthapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeDown
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.mentalhealthapp.R

@Composable
fun AnxietyScreen(navController: NavHostController) {
    val context = LocalContext.current
    val audioPlayer = remember { AnxietyAudioPlayer(context) }

    DisposableEffect(Unit) {
        audioPlayer.play()
        onDispose {
            audioPlayer.stop()
            audioPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F9FF))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Calm text at top
            Text(
                text = "Take a deep breath...",
                modifier = Modifier.padding(top = 48.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF4A6572)
            )

            // Centered Lottie animation
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.meditation)
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .size(400.dp)
                    .padding(16.dp)
            )

            // Bottom button with shadow and rounded corners
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(bottom = 48.dp)
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7BB6EA)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    "I Feel Calm Now",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
    }
}
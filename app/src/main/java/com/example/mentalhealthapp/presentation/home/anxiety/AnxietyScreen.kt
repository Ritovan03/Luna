package com.example.mentalhealthapp.presentation.home.anxiety

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
            .background(Color(0xFF61BDD3)) // Lighter, calmer blue background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Calmer text with softer color
            Text(
                text = "Take a deep breath...",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFF5B87A6),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Centered Lottie animation with softer padding
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
                    .size(360.dp)
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Simpler, more elegant button
            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .height(52.dp),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFF5B87A6)
                )
            ) {
                Text(
                    "I'm feeling calmer now",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}
package com.example.mentalhealthapp.presentation.home.anxiety

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
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
            .background(Color(0xFF61BDD3))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Take a deep breath...",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                color = Color.White,  // Changed to white for better visibility
                modifier = Modifier.padding(bottom = 16.dp)
            )

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

            Button(  // Changed to regular Button for better visibility
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2B5876),  // Deeper blue for button
                    contentColor = Color.White  // White text for contrast
                ),
                shape = RoundedCornerShape(35.dp)
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
package com.example.mentalhealthapp.presentation.haven.sensory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.colorResource
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
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun SensoryTherapyScreen1(mainNavController: NavHostController)
{
    val context = LocalContext.current
    val audioPlayer = remember { SensoryTherapyAudioPlayer(context, R.raw.forest_level1) }

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
            .background(color = colorResource(R.color.teal_700))
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
                text = "Level 1",
                fontSize = 36.sp,  // Increased from 28.sp to make it bigger
                fontWeight = FontWeight.Bold,
                color = Color.White  // Slightly greener than white
            )
            Text(
                text = "Birds chirping in a forest.\nTake a moment to relax and feel calm in this environment, once you feel ready, click the button below to proceed to next level",
                fontSize = 18.sp,  // Reduced from 28.sp to make it smaller
                fontWeight = FontWeight.Medium,  // Changed from Bold to Medium for the description
                color = Color.White,  // Light green color
                lineHeight = 24.sp  // Added for better readability
            )

            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.level1)
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Box(
                modifier = Modifier
                    .height(300.dp)  // Fixed height container
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier
                        .size(280.dp)  // Slightly smaller to ensure it fits in the box
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { mainNavController.navigate(Route.SensoryTherapy2.route) },
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor =colorResource(R.color.teal_700)  // Changed to teal_700 for better contrast
                )
            ) {
                Text(
                    "Proceed to Next Level",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
package com.example.mentalhealthapp.presentation.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun WelcomeScreen1(navController: NavHostController){
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Welcome 1", style = MaterialTheme.typography.headlineMedium)
//        Button(onClick = { navController.navigate(Route.Welcome2.route) }) {
//            Text("Next")
//        }
//    }
//}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // 🟤 App Logo
        Image(
            painter = painterResource(id = R.drawable.iconlogo),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )

        // 🌟 Welcome Text
        Text(
            text = "Welcome to Luna",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6D4C41),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 📝 Subtitle
        Text(
            text = "Your mindful mental health companion for everyone, anywhere 🌿",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 🎭 Lottie Animation - Luna Mascot
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.birdie))
        val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // 🟤 Get Started Button
        Button(
            onClick = { navController.navigate(Route.Welcome2.route) },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF507780)),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Get Started", fontSize = 18.sp, color = Color.White)
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Arrow", tint = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔸 Sign In Link
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account? ",
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 1
            )

            Text(
                text = "Sign In.",
                color = Color(0xFFD2691E),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { navController.navigate(Route.Login.route) }
            )
        }


    }
}
package com.example.mentalhealthapp.presentation.onboarding

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun WelcomeScreen2(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
             // Light beige background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Top Illustration
            Image(
                painter = painterResource(id = R.drawable.wc2),
                contentDescription = "Illustration",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Adjusts size proportionally
            )

            // Bottom Content Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(
                        Color.White, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .padding(vertical = 14.dp, horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Step Indicator (Optional, if needed)
//                Text(
//                    text = "Step One",
//                    color = Color(0xFF57341C),
//                    fontSize = 14.sp,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier
//                        .background(
//                            Color(0xFFF5F3E7),
//                            shape = RoundedCornerShape(16.dp)
//                        )
//                        .padding(horizontal = 12.dp, vertical = 6.dp)
//                )

                Spacer(modifier = Modifier.height(16.dp))

                // Main Text
                Text(
                    text = "Personalize Your Mental",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF57341C),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Health State With AI",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF628F58),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Progress Indicator (Optional)
                // WelcomeScreen2
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(4.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(50))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.333f) // 2/6 progress
                            .height(4.dp)
                            .background(Color(0xFF57341C), shape = RoundedCornerShape(50))
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Navigation Button
                Button(
                    onClick = { navController.navigate(Route.Welcome3.route) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF57341C)), // Brown color
                    shape = CircleShape,
                    modifier = Modifier.size(60.dp),
                    contentPadding = PaddingValues(0.dp) // Remove default padding
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Next",
                        modifier = Modifier.size(32.dp), // Correct icon size
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

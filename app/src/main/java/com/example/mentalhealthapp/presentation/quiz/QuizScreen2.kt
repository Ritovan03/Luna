package com.example.mentalhealthapp.presentation.quiz

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route


enum class Gender {
    MALE, FEMALE, NONE
}

@Composable
fun QuizScreen2(navController: NavHostController) {
    var selectedGender by remember { mutableStateOf(Gender.NONE) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F5F2))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar with back button and title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                // Back button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color(0xFF65635F), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "(",
                        color = Color(0xFF65635F),
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Assessment title
                Text(
                    text = "Assessment",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color(0xFF4A2B0F)
                )

                Spacer(modifier = Modifier.weight(1f))

                // Page indicator
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFEAE0D5))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "2 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Question Text
            Text(
                text = "What's your gender?",
                color = Color(0xFF3E3A33),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Male Option - Using full images for both states as requested
            // Male Option
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)  // Fixed height for both options
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { selectedGender = Gender.MALE }
            ) {
                Image(
                    painter = painterResource(
                        id = if (selectedGender == Gender.MALE) {
                            R.drawable.selected_man
                        } else {
                            R.drawable.unselected_man
                        }
                    ),
                    contentDescription = "Male Option",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop  // Use Crop to maintain aspect ratio
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

// Female Option
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .size(160.dp)// Same fixed height as male option
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { selectedGender = Gender.FEMALE }
            ) {
                Image(
                    painter = painterResource(
                        id = if (selectedGender == Gender.FEMALE) {
                            R.drawable.selected_woman
                        } else {
                            R.drawable.unselected_woman
                        }
                    ),
                    contentDescription = "Female Option",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds  // Use Crop to maintain aspect ratio
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Skip Button
            Surface(
                color = Color(0xFFD1DCC0),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate(Route.Quiz3.route)
                        Log.d("QuizScreen2", "Skip button clicked")
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 12.dp)
                ) {
                    Text(
                        text = "Prefer to skip, thanks",
                        color = Color(0xFF65635F),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = "Skip",
                        tint = Color(0xFF65635F),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Continue Button
            Button(
                onClick = {
                    navController.navigate(Route.Quiz3.route)
                    Log.d("QuizScreen2", "${selectedGender} button clicked")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF64422C),
                    contentColor = Color.White,
                ),
                enabled = selectedGender != Gender.NONE,
                shape = RoundedCornerShape(28.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Continue",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Continue"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreen2Preview() {
    val navController = rememberNavController()
    QuizScreen1(navController = navController)
}




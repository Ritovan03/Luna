package com.example.mentalhealthapp.presentation.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.ui.platform.LocalContext
import com.airbnb.lottie.compose.*
import com.example.mentalhealthapp.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun QuizScreen11(navController: NavHostController) {
    val backgroundColor = Color(0xFFF8F5F1)
    val greenColor = Color(0xFF8DB06F)
    val brownColor = Color(0xFF64422C)
    val textBrownColor = Color(0xFF5A3C2C)

    var situationInput by remember { mutableStateOf("") }
    var situations by remember { mutableStateOf(listOf<String>()) }
    val maxSituations = 5

    val keyboardController = LocalSoftwareKeyboardController.current

    // Lottie animation setup with anxiety.json
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anxiety)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 1f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top bar (remains the same as in the original code)
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
                        text = "11 of 15",
                        fontSize = 14.sp,
                        color = Color(0xFF4A2B0F),
                        modifier = Modifier.background(Color(0xFFEAE0D5))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "I feel most overwhelmed when...",
                modifier = Modifier.fillMaxWidth(),
                color = textBrownColor,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Lottie animation with error handling
            composition?.let {
                LottieAnimation(
                    composition = it,
                    progress = { progress },
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Rest of the code remains the same as the original implementation
            // (Input surface, continue button, etc.)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                border = BorderStroke(1.dp, greenColor)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .fillMaxWidth()
                        ) {
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                mainAxisSpacing = 8.dp,
                                crossAxisSpacing = 12.dp
                            ) {
                                situations.forEach { situation ->
                                    AssistChip(
                                        onClick = { },
                                        label = { Text(situation) },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = greenColor.copy(alpha = 0.2f),
                                            labelColor = greenColor
                                        ),
                                        trailingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Remove",
                                                modifier = Modifier
                                                    .size(16.dp)
                                                    .clickable {
                                                        situations = situations.filter { it != situation }
                                                    },
                                                tint = greenColor
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }

                    OutlinedTextField(
                        value = situationInput,
                        onValueChange = { situationInput = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        placeholder = { Text("Type a situation...") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                            containerColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (situationInput.isNotBlank() && situations.size < maxSituations) {
                                    situations = situations + situationInput.trim()
                                    situationInput = ""
                                }
                                keyboardController?.hide()
                            }
                        ),
                        singleLine = true
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_dialog_info),
                                contentDescription = "Info",
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${situations.size}/$maxSituations",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Continue button
            Button(
                onClick = {
                    Log.d("OverwhelmingSituations", "Selected Situations: ${situations.joinToString(", ")}")
                    navController.navigate(Route.Quiz12.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
//                    .align(Alignment.BottomCenter)
//                    ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = brownColor,
                    contentColor = Color.White
                ),
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
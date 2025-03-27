package com.example.mentalhealthapp.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen1(navController: NavHostController) {
    // Define colors as per design
    val backgroundColor = Color(0xFFEAF8FF) // Soft pastel blue background
    val titleTextColor = Color(0xFF1A2238) // Deep navy blue for title
    val subtitleTextColor = Color(0xFF6B7280) // Soft gray for subtitle
    val buttonColor = Color(0xFF35A9AF) // Teal button color
    val buttonTextColor = Color(0xFFFFFFFF) // White text
    val signInTextColor = Color(0xFF1A2238) // Deep navy blue for "Already have an account?"
    val tealBorderColor = Color(0xFF35A9AF) // Teal border for Sign In button

    // Animation states
    var isLogoVisible by remember { mutableStateOf(false) }
    var isTitleVisible by remember { mutableStateOf(false) }
    var isSubtitleVisible by remember { mutableStateOf(false) }
    var isLottieVisible by remember { mutableStateOf(false) }
    var isButtonVisible by remember { mutableStateOf(false) }
    var isSignInVisible by remember { mutableStateOf(false) }

    // Sequential animations
    LaunchedEffect(Unit) {
        isLogoVisible = true
        delay(300)
        isTitleVisible = true
        delay(300)
        isSubtitleVisible = true
        delay(300)
        isLottieVisible = true
        delay(300)
        isButtonVisible = true
        delay(300)
        isSignInVisible = true
    }

    // Infinite transition for pulsing button
    val infiniteTransition = rememberInfiniteTransition(label = "buttonPulse")
    val buttonScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "buttonPulse"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // App Logo with animation
        AnimatedVisibility(
            visible = isLogoVisible,
            enter = fadeIn(animationSpec = tween(500)) +
                    slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500))
        ) {
            Image(
                painter = painterResource(id = R.drawable.iconlogo),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )
        }

        // Welcome Text with animation
        AnimatedVisibility(
            visible = isTitleVisible,
            enter = fadeIn(animationSpec = tween(500)) +
                    slideInVertically(initialOffsetY = { -it }, animationSpec = tween(500))
        ) {
            Text(
                text = "Welcome to Luna",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = titleTextColor,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Subtitle with animation
        AnimatedVisibility(
            visible = isSubtitleVisible,
            enter = fadeIn(animationSpec = tween(500)) +
                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(500))
        ) {
            Text(
                text = "✨ Navigate life with comfort—gentle guidance, \uD83D\uDC99 emotional clarity, and a community \uD83E\uDD1D that truly sees and supports you. \uD83C\uDF3F",
                fontSize = 18.sp,
                color = subtitleTextColor,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Lottie Animation with fade-in
        AnimatedVisibility(
            visible = isLottieVisible,
            enter = fadeIn(animationSpec = tween(800))
        ) {
            // Lottie Animation - Luna Mascot
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.birdie))
            val progress by animateLottieCompositionAsState(
                composition, iterations = LottieConstants.IterateForever
            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Get Started Button with pulsing animation
        AnimatedVisibility(
            visible = isButtonVisible,
            enter = fadeIn(animationSpec = tween(800)) +
                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(500))
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            val buttonScalePressed by animateFloatAsState(
                targetValue = if (isPressed) 0.95f else 1f,
                animationSpec = tween(100),
                label = "pressAnimation"
            )

            Button(
                onClick = { navController.navigate(Route.Welcome2.route) },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp, pressedElevation = 8.dp
                ),
                interactionSource = interactionSource,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .scale(buttonScale * buttonScalePressed)
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    color = buttonTextColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Arrow",
                    tint = buttonTextColor
                )
            }
        }

        // Sign In Link with animation
        AnimatedVisibility(
            visible = isSignInVisible,
            enter = fadeIn(animationSpec = tween(800)) +
                    slideInVertically(initialOffsetY = { it }, animationSpec = tween(500))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Already have an account? ",
                    color = signInTextColor,
                    fontSize = 16.sp
                )

                val interactionSource = remember { MutableInteractionSource() }
                val isPressed by interactionSource.collectIsPressedAsState()
                val textScale by animateFloatAsState(
                    targetValue = if (isPressed) 1.1f else 1f,
                    animationSpec = tween(100),
                    label = "signInPressAnimation"
                )

                Text(
                    text = "Sign In",
                    color = tealBorderColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .scale(textScale)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = rememberRipple(color = tealBorderColor)
                        ) { navController.navigate(Route.Login.route) }
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreen1Preview() {
    WelcomeScreen1(navController = rememberNavController())
}
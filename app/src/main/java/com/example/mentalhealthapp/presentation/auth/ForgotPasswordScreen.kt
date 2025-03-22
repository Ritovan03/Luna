package com.example.mentalhealthapp.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgotPasswordScreen(
//    onBackClick: () -> Unit,
//    onSendPasswordClick: () -> Unit
) {
    val brownColor = Color(0xFF59473E)
    val greenColor = Color(0xFFA7B99E)
    val lightGreenColor = Color(0xFFE8EDE5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        // Back Button
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Title and Description
        Text(
            text = "Forgot Password",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = brownColor
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Select contact details where you want to reset your password.",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Option Cards
        ResetOption(
            title = "Use 2FA",
            icon = Icons.Default.Lock,
            backgroundColor = lightGreenColor,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResetOption(
            title = "Password",
            icon = Icons.Default.Lock,
            backgroundColor = lightGreenColor,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        ResetOption(
            title = "Google Authenticator",
            icon = Icons.Default.Lock,
            backgroundColor = lightGreenColor,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        // Send Password Button
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = brownColor),
            shape = RoundedCornerShape(32.dp)
        ) {
            Text(
                text = "Send Password",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
private fun ResetOption(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF59473E),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color(0xFF59473E),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(modifier: Modifier = Modifier) {
    ForgotPasswordScreen()
}
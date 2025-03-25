package com.example.mentalhealthapp.presentation.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import kotlinx.coroutines.launch

class WaveShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, size.height * 0.55f)
            cubicTo(
                size.width * 0.25f, size.height * 0.95f,
                size.width * 0.75f, size.height * 0.95f,
                size.width, size.height * 0.5f
            )
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun SignInScreen(viewModel: AuthViewModel, navController: NavHostController) {
    var isSignIn by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val authState by viewModel.authState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    // Navigation effect
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            navController.navigate(Route.Todo.route) {
                popUpTo(Route.Login.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.offwhite_screen_color)),
        contentAlignment = Alignment.Center
    ) {
        // Handle auth states
        when (authState) {
            is AuthState.Initial -> {
                // Initial State
            }
            is AuthState.Error -> {
                // Change state to show error
            }
            AuthState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(R.color.brown)
                )
            }
            is AuthState.Success -> {
                // Navigation is handled by LaunchedEffect
            }
        }

        Image(
            painter = painterResource(id = R.drawable.auth_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Title
           Text(
               text = if (isSignIn) "Sign In To Luna" else "Sign Up For Luna",
               style = MaterialTheme.typography.headlineMedium,
               fontWeight = FontWeight.Bold,
               color = colorResource(R.color.brown),
               modifier = Modifier.padding(bottom = 32.dp, top = 72.dp)
           )

            // Username field (only for sign up)
            if (!isSignIn) {
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.brown),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 8.dp)
                )

                OutlinedTextField(
                    value = formState.username,
                    onValueChange = { viewModel.updateUsername(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    placeholder = { Text("Enter your username") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Username Icon",
                            tint = Color.Gray
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.Black,
                        focusedBorderColor = colorResource(R.color.brown)
                    )
                )
            }

            // Email field
            Text(
                text = "Email Address",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.brown),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = formState.email,
                onValueChange = { viewModel.updateEmail(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(24.dp),
                placeholder = { Text("Enter your email") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color.Gray
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = colorResource(R.color.brown),
                    focusedTextColor = colorResource(R.color.brown),
                    unfocusedTextColor = Color.DarkGray
                )
            )

            // Password field
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.brown),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = formState.password,
                onValueChange = { viewModel.updatePassword(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(24.dp),
                placeholder = { Text("Enter your password...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password Icon",
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        androidx.compose.material.Icon(
                            imageVector = if (passwordVisible)
                                Icons.Filled.Visibility
                            else
                                Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            tint = Color.Gray
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = colorResource(R.color.brown),
                    focusedTextColor = colorResource(R.color.brown),
                    unfocusedTextColor = Color.DarkGray
                )
            )

            // Sign In/Up Button
           Button(
                onClick = {
                    if (isSignIn) {
                        viewModel.signIn(formState.email, formState.password)
                    } else {
                        viewModel.signUp(formState.email, formState.password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.brown)
                )
            ) {
                Text(
                    text = if (isSignIn) "Sign In" else "Sign Up",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = if (isSignIn) "Sign In" else "Sign Up",
                    tint = Color.White
                )
            }

            // Google Sign In Button
            Spacer(modifier = Modifier.height(16.dp))

          GoogleSignInButton {
              coroutineScope.launch {
                  viewModel.signInWithGoogle(context)
              }
          }


            // Sign Up and Forgot Password
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isSignIn) "Don't have an account? " else "Already have an account? ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
                Text(
                    text = if (isSignIn) "Sign Up" else "Sign In",
                    modifier = Modifier.clickable {
                        isSignIn = !isSignIn
                        // Clear fields on switch
                        viewModel.updateEmail("")
                        viewModel.updatePassword("")
                        if (!isSignIn) {
                            viewModel.updateUsername("")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.orange_Accent_color)
                )
            }

            if (isSignIn) {
                TextButton(
                    onClick = { /* onForgotPassword implementation */ },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = "Forgot Password",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(R.color.orange_Accent_color)
                    )
                }
            }
        }
    }
}

@Composable
fun GoogleSignInButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF9BB168)
        ),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_icon),
                contentDescription = "Google Icon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Sign in with Google",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.brown)
            )
        }
    }
}
@Composable
fun SocialButton(
    icon: Int,
    contentDescription: String,
    onClick : () -> Unit
) {
    OutlinedButton(
        onClick = {  onClick()},
        modifier = Modifier.size(56.dp),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.fillMaxSize()
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ScreenPreview() {
//   LoginScreen()
//}
//

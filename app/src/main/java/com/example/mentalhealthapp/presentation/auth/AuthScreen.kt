package com.example.mentalhealthapp.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mentalhealthapp.R

@Composable
fun AuthCard(modifier: Modifier = Modifier,
             viewModel: AuthViewModel){

    var isSignIn by remember { mutableStateOf(true) }

    val authState by viewModel.authState.collectAsState()
    val formState by viewModel.uiState.collectAsState()

    when(authState){
        is AuthState.Initial -> {
          //Initial State
        }
        is AuthState.Error -> {
            //Change state to show error
        }
        AuthState.Loading -> {
            CircularProgressIndicator()
        }
        is AuthState.Success -> {
            val user = (authState as AuthState.Success).user
            //Navigate the user
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Title
                Text(
                    text = if (isSignIn) "Sign In" else "Sign Up",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (!isSignIn) {
                    OutlinedTextField(
                        value = formState.username,
                        onValueChange = {viewModel.updateUsername(it)},
                        label = { Text("Username") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                }

                OutlinedTextField(
                    value = formState.email,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                OutlinedTextField(
                    value = formState.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

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
                        .height(48.dp)
                ) {
                    Text(text = if (isSignIn) "Sign In" else "Sign Up")
                }

                TextButton(onClick = {
                    isSignIn = !isSignIn
                    //Clear the email and password fields.
                }) {
                    Text(
                        text = if (isSignIn) "Need an account? Sign Up" else "Already have an account? Sign In"
                    )
                }
            }
        }
    }
}
//
//@Composable
//fun AuthScreen(viewModel: AuthViewModel) {
//    var isSignIn by remember { mutableStateOf(true) }
//    var passwordVisible by remember { mutableStateOf(false) }
//
//
//    val authState by viewModel.authState.collectAsState()
//    val formState by viewModel.uiState.collectAsState()
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.offwhite_screen_color)),
//        contentAlignment = Alignment.Center
//    ) {
//        // Handle auth states
//        when (authState) {
//            is AuthState.Initial -> {
//                //Initial State
//            }
//            is AuthState.Error -> {
//                //Change state to show error
//            }
//            AuthState.Loading -> {
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center),
//                    color = colorResource(R.color.purple_800)
//                )
//            }
//            is AuthState.Success -> {
//                val user = (authState as AuthState.Success).user
//                //Navigate the user
//            }
//        }
//
//        Image(
//            painter = painterResource(id = R.drawable.auth_bg),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.matchParentSize()
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            // Title
//            Text(
//                text = if (isSignIn) "Sign In To Luna" else "Sign Up For Luna",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
//                color = Color.DarkGray,
//                modifier = Modifier.padding(bottom = 32.dp)
//            )
//
//            // Username field (only for sign up)
//            if (!isSignIn) {
//                Text(
//                    text = "Username",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = colorResource(R.color.purple_800),
//                    modifier = Modifier
//                        .align(Alignment.Start)
//                        .padding(bottom = 8.dp)
//                )
//
//                OutlinedTextField(
//                    value = formState.username,
//                    onValueChange = { viewModel.updateUsername(it) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp),
//                    shape = RoundedCornerShape(24.dp),
//                    placeholder = { Text("Enter your username") },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Person,
//                            contentDescription = "Username Icon",
//                            tint = Color.Gray
//                        )
//                    },
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Text,
//                        imeAction = ImeAction.Next
//                    ),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedBorderColor = Color.Black,
//                        focusedBorderColor = colorResource(R.color.purple_800)
//                    )
//                )
//            }
//
//            // Email field
//            Text(
//                text = "Email Address",
//                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(R.color.purple_800),
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(bottom = 8.dp)
//            )
//
//            OutlinedTextField(
//                value = formState.email,
//                onValueChange = { viewModel.updateEmail(it) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp),
//                shape = RoundedCornerShape(24.dp),
//                placeholder = { Text("Enter your email") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Email,
//                        contentDescription = "Email Icon",
//                        tint = Color.Gray
//                    )
//                },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Email,
//                    imeAction = ImeAction.Next
//                ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedBorderColor = Color.Black,
//                    focusedBorderColor = colorResource(R.color.purple_800)
//                )
//            )
//
//            // Password field
//            Text(
//                text = "Password",
//                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(R.color.purple_800),
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(bottom = 8.dp)
//            )
//
//            OutlinedTextField(
//                value = formState.password,
//                onValueChange = { viewModel.updatePassword(it) },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 24.dp),
//                shape = RoundedCornerShape(24.dp),
//                placeholder = { Text("Enter your password...") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = "Password Icon",
//                        tint = Color.Gray
//                    )
//                },
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = if (passwordVisible)
//                                Icons.Filled.Visibility
//                            else
//                                Icons.Filled.VisibilityOff,
//                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
//                            tint = Color.Gray
//                        )
//                    }
//                },
//                singleLine = true,
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Password,
//                    imeAction = ImeAction.Done
//                ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedBorderColor = Color.Black,
//                    focusedBorderColor = colorResource(R.color.purple_800)
//                )
//            )
//
//            // Sign In/Up Button
//            Button(
//                onClick = {
//                    if (isSignIn) {
//                        viewModel.signIn(formState.email, formState.password)
//                    } else {
//                        viewModel.signUp(formState.email, formState.password)
//                    }
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                shape = RoundedCornerShape(24.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = colorResource(R.color.purple_800)
//                )
//            ) {
//                Text(
//                    text = if (isSignIn) "Sign In" else "Sign Up",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = if (isSignIn) "Sign In" else "Sign Up"
//                )
//            }
//
//            // Sign Up and Forgot Password
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = if (isSignIn) "Don't have an account? " else "Already have an account? ",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = Color.DarkGray
//                )
//                Text(
//                    text = if (isSignIn) "Sign Up" else "Sign In",
//                    modifier = Modifier.clickable {
//                        isSignIn = !isSignIn
//                        // Clear fields on switch
//                        viewModel.updateEmail("")
//                        viewModel.updatePassword("")
//                        if (!isSignIn) {
//                            viewModel.updateUsername("")
//                        }
//                    },
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = colorResource(R.color.orange_Accent_color)
//                )
//            }
//
//            if (isSignIn) {
//                TextButton(
//                    onClick = { /* onForgotPassword implementation */ },
//                    contentPadding = PaddingValues(0.dp)
//                ) {
//                    Text(
//                        text = "Forgot Password",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = colorResource(R.color.orange_Accent_color)
//                    )
//                }
//            }
//        }
//    }
//}
//

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
//    AuthScreenPreviewContent(isSignInScreen = true)
}

//@Preview(showBackground = true)
//@Composable
//fun AuthScreenSignUpPreview() {
//    AuthScreenPreviewContent(isSignInScreen = false)
//}
//
//@Composable
//private fun AuthScreenPreviewContent(isSignInScreen: Boolean) {
//    var isSignIn by remember { mutableStateOf(isSignInScreen) }
//    var passwordVisible by remember { mutableStateOf(false) }
//    var email by remember { mutableStateOf("user@example.com") }
//    var password by remember { mutableStateOf("password123") }
//    var username by remember { mutableStateOf("JaneDoe") }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(R.color.offwhite_screen_color)),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.auth_bg),
//            contentDescription = null,
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.matchParentSize()
//        )
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(horizontal = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            // Title
//            Text(
//                text = if (isSignIn) "Sign In To Luna" else "Sign Up For Luna",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
//                color = Color.DarkGray,
//                modifier = Modifier.padding(bottom = 32.dp)
//            )
//
//            // Username field (only for sign up)
//            if (!isSignIn) {
//                Text(
//                    text = "Username",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = colorResource(R.color.purple_800),
//                    modifier = Modifier
//                        .align(Alignment.Start)
//                        .padding(bottom = 8.dp)
//                )
//
//                OutlinedTextField(
//                    value = username,
//                    onValueChange = { username = it },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp),
//                    shape = RoundedCornerShape(24.dp),
//                    placeholder = { Text("Enter your username") },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Person,
//                            contentDescription = "Username Icon",
//                            tint = Color.Gray
//                        )
//                    },
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Text,
//                        imeAction = ImeAction.Next
//                    ),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        unfocusedBorderColor = Color.Black,
//                        focusedBorderColor = colorResource(R.color.purple_800)
//                    )
//                )
//            }
//
//            // Email field
//            Text(
//                text = "Email Address",
//                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(R.color.purple_800),
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(bottom = 8.dp)
//            )
//
//            OutlinedTextField(
//                value = email,
//                onValueChange = { email = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp),
//                shape = RoundedCornerShape(24.dp),
//                placeholder = { Text("Enter your email") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Email,
//                        contentDescription = "Email Icon",
//                        tint = Color.Gray
//                    )
//                },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Email,
//                    imeAction = ImeAction.Next
//                ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedBorderColor = Color.Black,
//                    focusedBorderColor = colorResource(R.color.purple_800)
//                )
//            )
//
//            // Password field
//            Text(
//                text = "Password",
//                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(R.color.purple_800),
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(bottom = 8.dp)
//            )
//
//            OutlinedTextField(
//                value = password,
//                onValueChange = { password = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 24.dp),
//                shape = RoundedCornerShape(24.dp),
//                placeholder = { Text("Enter your password...") },
//                leadingIcon = {
//                    Icon(
//                        imageVector = Icons.Default.Lock,
//                        contentDescription = "Password Icon",
//                        tint = Color.Gray
//                    )
//                },
//                trailingIcon = {
//                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                        Icon(
//                            imageVector = if (passwordVisible)
//                                Icons.Filled.Visibility
//                            else
//                                Icons.Filled.VisibilityOff,
//                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
//                            tint = Color.Gray
//                        )
//                    }
//                },
//                singleLine = true,
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Password,
//                    imeAction = ImeAction.Done
//                ),
//                colors = OutlinedTextFieldDefaults.colors(
//                    unfocusedBorderColor = Color.Black,
//                    focusedBorderColor = colorResource(R.color.purple_800)
//                )
//            )
//
//            // Sign In/Up Button
//            Button(
//                onClick = { /* Preview only - no action */ },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(56.dp),
//                shape = RoundedCornerShape(24.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = colorResource(R.color.purple_800)
//                )
//            ) {
//                Text(
//                    text = if (isSignIn) "Sign In" else "Sign Up",
//                    style = MaterialTheme.typography.bodyLarge
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Icon(
//                    imageVector = Icons.Default.ArrowForward,
//                    contentDescription = if (isSignIn) "Sign In" else "Sign Up"
//                )
//            }
//
//            // Sign Up and Forgot Password
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = if (isSignIn) "Don't have an account? " else "Already have an account? ",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = Color.DarkGray
//                )
//                Text(
//                    text = if (isSignIn) "Sign Up" else "Sign In",
//                    modifier = Modifier.clickable { isSignIn = !isSignIn },
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = colorResource(R.color.orange_Accent_color)
//                )
//            }
//
//            if (isSignIn) {
//                TextButton(
//                    onClick = { /* Preview only - no action */ },
//                    contentPadding = PaddingValues(0.dp)
//                ) {
//                    Text(
//                        text = "Forgot Password",
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = colorResource(R.color.orange_Accent_color)
//                    )
//                }
//            }
//        }
//    }
//}
//
//

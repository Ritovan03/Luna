package com.example.mentalhealthapp.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun AuthCard(modifier: Modifier = Modifier,
             viewModel: AuthViewModel) {

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
                        if(isSignIn){
                            viewModel.signIn(formState.email,formState.password)
                        }else{
                            viewModel.signUp(formState.email,formState.password)
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


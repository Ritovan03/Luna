package com.example.mentalhealthapp.presentation.auth

import androidx.compose.foundation.background
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.ui.theme.UrbanistFont
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.presentation.Navigation.Route

@Composable
fun SignupScreen(viewModel: AuthViewModel) {

    var passwordVisible by remember { mutableStateOf(false) }

    val authState by viewModel.authState.collectAsState()
    val formState by viewModel.uiState.collectAsState()



    val greenColor = Color(0xFF9BB168)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    color = greenColor,
                    shape = WaveShape()
                )
        ) {
            // Logo
//            Image(
//                painter = painterResource(id = R.drawable.logo),
//                contentDescription = "Logo",
//                modifier = Modifier
//                    .size(48.dp)
//                    .align(Alignment.Center)
        }//            )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            Text(
                text = "Sign Up For Free",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = UrbanistFont,
                color = colorResource(id = R.color.brown),
                modifier = Modifier.padding(top = 20.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                Text(
                    text = "Email Address",
                    color = colorResource(id = R.color.brown),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = UrbanistFont,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = formState.email,
                    onValueChange = { viewModel.updateEmail(it)},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                    placeholder = { Text("Enter your email",   fontFamily = UrbanistFont,) },
                    leadingIcon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_launcher_background),
//                            contentDescription = "Email",
//                            tint = greenColor
//                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = greenColor,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Password",
                    color = colorResource(id = R.color.brown),
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = formState.password,
                    onValueChange = { viewModel.updatePassword(it) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                    placeholder = { Text("Enter your password...",   fontFamily = UrbanistFont,) },
                    leadingIcon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_launcher_background),
//                            contentDescription = "Password",
//                            tint = greenColor
//                        )
                    },
                    trailingIcon = {
//                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                            Icon(
//                                painter = painterResource(
//                                    id = if (passwordVisible) R.drawable.ic_launcher_background
//                                    else R.drawable.ic_launcher_background
//                                ),
//                                contentDescription = "Toggle password visibility",
//                                tint = greenColor
//                            )
//                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = greenColor,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true
                )

            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Password Confirmation",
                    color = colorResource(id = R.color.brown),
                    fontFamily = UrbanistFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = {  },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                    placeholder = { Text("Confirm your password...",   fontFamily = UrbanistFont,) },
                    leadingIcon = {
//                        Icon(
//                            painter = painterResource(id = R.drawable.ic_launcher_background),
//                            contentDescription = "Password",
//                            tint = greenColor
//                        )
                    },
                    trailingIcon = {
//                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
//                            Icon(
//                                painter = painterResource(
//                                    id = if (passwordVisible) R.drawable.ic_launcher_background
//                                    else R.drawable.ic_launcher_background
//                                ),
//                                contentDescription = "Toggle password visibility",
//                                tint = greenColor
//                            )
//                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = greenColor,
                        unfocusedBorderColor = Color.LightGray
                    ),
                    singleLine = true
                )

            }

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = { viewModel.signUp(formState.email,formState.password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.brown)),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = "Sign In",
                    color = Color.White,
                    fontFamily = UrbanistFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_launcher_background),
//                    contentDescription = "Sign In",
//                    tint = Color.White,
//                    modifier = Modifier.padding(start = 8.dp)
//                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account? ",
                    fontFamily = UrbanistFont,
                    color = Color.Gray,
                    fontSize = 18.sp
                )
                Text(
                    text = "Sign In",
                    fontFamily = UrbanistFont,
                    color = Color(0xFFFF6B00),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewSignUpScreen(modifier: Modifier = Modifier) {
//      SignupScreen()
//}
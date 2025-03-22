package com.example.mentalhealthapp.presentation.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.BuildCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mentalhealthapp.R
import com.example.mentalhealthapp.presentation.Navigation.Route
import com.example.mentalhealthapp.ui.theme.UrbanistFont
import com.google.firebase.BuildConfig
import kotlinx.coroutines.coroutineScope
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
fun SignInScreen(viewModel: AuthViewModel,navController: NavHostController) {

    val formState by viewModel.uiState.collectAsState()
    val authState by viewModel.authState.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(authState){
        if(authState is AuthState.Success){
            navController.navigate(Route.Home.route){
                popUpTo(Route.Login.route){inclusive = true}
            }
        }
    }

    val greenColor = Color(0xFF9BB168)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top curved background
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
                text = "Sign In To freud.ai",
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
                    onValueChange = { viewModel.updateEmail(it) },
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
                    onValueChange = { viewModel.updatePassword(it)},
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

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = { viewModel.signIn(formState.email,formState.password) },
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

            Spacer(modifier = Modifier.height(32.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
//                SocialButton(
//                    icon = R.drawable.ic_launcher_background,
//                    contentDescription = "Facebook"
//                )
                Spacer(modifier = Modifier.width(16.dp))
                SocialButton(
                    icon = R.drawable.google_icon,
                    contentDescription = "Google",
                    onClick = {
                      coroutineScope.launch {
                          viewModel.signInWithGoogle()
                      }
                    }
                )
                Spacer(modifier = Modifier.width(16.dp))
//                SocialButton(
//                    icon = R.drawable.ic_launcher_background,
//                    contentDescription = "Instagram"
//                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontFamily = UrbanistFont,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
                Text(
                    text = "Sign Up",
                    fontFamily = UrbanistFont,
                    color = Color(0xFFFF6B00),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Forgot Password",
                color = Color(0xFFFF6B00),
                fontWeight = FontWeight.Bold
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

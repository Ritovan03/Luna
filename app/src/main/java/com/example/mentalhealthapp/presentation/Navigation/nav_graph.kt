package com.example.mentalhealthapp.presentation.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mentalhealthapp.presentation.auth.AuthViewModel
import com.example.mentalhealthapp.presentation.auth.ForgotPasswordScreen
import com.example.mentalhealthapp.presentation.auth.ResetPasswordScreen
import com.example.mentalhealthapp.presentation.auth.SignInScreen
import com.example.mentalhealthapp.presentation.auth.SignupScreen
import com.example.mentalhealthapp.presentation.auth.ToDoViewModel
import com.example.mentalhealthapp.presentation.auth.TodoListScreen
import com.example.mentalhealthapp.presentation.haven.HavenScreen
import com.example.mentalhealthapp.presentation.haven.to_do.ToDoScreen
import com.example.mentalhealthapp.presentation.home.AnxietyScreen
import com.example.mentalhealthapp.presentation.home.HomeScreen
import com.example.mentalhealthapp.presentation.onboarding.SplashScreen
import com.example.mentalhealthapp.presentation.onboarding.WelcomeScreen1
import com.example.mentalhealthapp.presentation.onboarding.WelcomeScreen2
import com.example.mentalhealthapp.presentation.onboarding.WelcomeScreen3
import com.example.mentalhealthapp.presentation.onboarding.WelcomeScreen4
import com.example.mentalhealthapp.presentation.onboarding.WelcomeScreen5
import com.example.mentalhealthapp.presentation.onboarding.WelcomeScreen6
import com.example.mentalhealthapp.presentation.profile.ProfileScreen
import com.example.mentalhealthapp.presentation.quiz.QuiZScreen14
import com.example.mentalhealthapp.presentation.quiz.QuizScreen1
import com.example.mentalhealthapp.presentation.quiz.QuizScreen10
import com.example.mentalhealthapp.presentation.quiz.QuizScreen11
import com.example.mentalhealthapp.presentation.quiz.QuizScreen12
import com.example.mentalhealthapp.presentation.quiz.QuizScreen13
import com.example.mentalhealthapp.presentation.quiz.QuizScreen15
import com.example.mentalhealthapp.presentation.quiz.QuizScreen2
import com.example.mentalhealthapp.presentation.quiz.QuizScreen3
import com.example.mentalhealthapp.presentation.quiz.QuizScreen4
import com.example.mentalhealthapp.presentation.quiz.QuizScreen5
import com.example.mentalhealthapp.presentation.quiz.QuizScreen6
import com.example.mentalhealthapp.presentation.quiz.QuizScreen7
import com.example.mentalhealthapp.presentation.quiz.QuizScreen8
import com.example.mentalhealthapp.presentation.quiz.QuizScreen9
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

//@Composable
//fun Navigation(startDestination : String){
//
//    val navController = rememberNavController()
//
//    NavHost(navController = navController,startDestination = startDestination){
//       composable(route = Route.AuthRoute.route){
//             val authViewModel : AuthViewModel = hiltViewModel()
//             AuthCard(viewModel = authViewModel)
//       }
//    }
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    val authViewModel: AuthViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Route.Splash.route) { SplashScreen(navController) }

        composable(Route.Welcome1.route) { WelcomeScreen1(navController) }
        composable(Route.Welcome2.route) { WelcomeScreen2(navController) }
        composable(Route.Welcome3.route) { WelcomeScreen3(navController) }
        composable(Route.Welcome4.route) { WelcomeScreen4(navController) }
        composable(Route.Welcome5.route) { WelcomeScreen5(navController) }
        composable(Route.Welcome6.route) { WelcomeScreen6(navController) }

        composable(Route.Login.route) {
            SignInScreen(authViewModel, navController)
        }
        composable(Route.Signup.route) {
            SignupScreen(authViewModel)
        }

        composable(Route.ForgotPassword.route) { ForgotPasswordScreen() }
        composable(Route.ResetPassword.route) { ResetPasswordScreen(navController) }

        composable(Route.Quiz1.route) { QuizScreen1(navController) }
        composable(Route.Quiz2.route) { QuizScreen2(navController) }
        composable(Route.Quiz3.route) { QuizScreen3(navController) }
        composable(Route.Quiz4.route) { QuizScreen4(navController) }
        composable(Route.Quiz5.route) { QuizScreen5(navController) }
        composable(Route.Quiz6.route) { QuizScreen6(navController) }
        composable(Route.Quiz7.route) { QuizScreen7(navController) }
        composable(Route.Quiz8.route) { QuizScreen8(navController) }
        composable(Route.Quiz9.route) { QuizScreen9(navController) }
        composable(Route.Quiz10.route) { QuizScreen10(navController) }
        composable(Route.Quiz11.route) { QuizScreen11(navController) }
        composable(Route.Quiz12.route) { QuizScreen12(navController) }
        composable(Route.Quiz13.route) { QuizScreen13(navController) }
        composable(Route.Quiz14.route) { QuiZScreen14(navController) }
        composable(Route.Quiz15.route) { QuizScreen15(navController) }

        composable(Route.Home.route) { HomeScreen(navController) }
        composable(Route.Haven.route) { HavenScreen(navController) }

        composable(Route.Anxiety.route) { AnxietyScreen(navController) }

        composable(Route.Profile.route) { ProfileScreen(navController) }

        composable(Route.Todo.route) { ToDoScreen(navController) }

        composable(Route.Todo.route) {
            val viewModel: ToDoViewModel = hiltViewModel()
            TodoListScreen(viewModel)
        }
    }
}

















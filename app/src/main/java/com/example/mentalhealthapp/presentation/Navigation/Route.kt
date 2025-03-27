package com.example.mentalhealthapp.presentation.Navigation

sealed class Route(
    val route : String
){
  object AuthRoute: Route("AuthScreen")

    object Splash : Route("splash")

    object Welcome : Route("welcome")
    object Welcome1 : Route("welcome1")
    object Welcome2 : Route("welcome2")
    object Welcome3 : Route("welcome3")
    object Welcome4 : Route("welcome4")
    object Welcome5 : Route("welcome5")
    object Welcome6 : Route("welcome6")

    object Login : Route("login")
    object Signup : Route("signup")
    object ForgotPassword : Route("forgot_password")
    object ResetPassword : Route("reset_password")

    object Quiz1 : Route("quiz1")
    object Quiz2 : Route("quiz2")
    object Quiz3 : Route("quiz3")
    object Quiz4 : Route("quiz4")
    object Quiz5 : Route("quiz5")
    object Quiz6 : Route("quiz6")
    object Quiz7 : Route("quiz7")
    object Quiz8 : Route("quiz8")
    object Quiz9 : Route("quiz9")
    object Quiz10 : Route("quiz10")
    object Quiz11 : Route("quiz11")
    object Quiz12 : Route("quiz12")
    object Quiz13 : Route("quiz13")
    object Quiz14 : Route("quiz14")
    object Quiz15 : Route("quiz15")

    object Home : Route("home")
    object Haven : Route("haven")

    object Anxiety : Route("anxiety")
  object Chatbot: Route("chatbot")


  object Profile: Route("profile_screen")
  object Chat: Route("chat")

  object Todo: Route("todo_screen")


    object Profile: Route("profile_screen")


    object Todo : Route("todo")
}
package com.example.mentalhealthapp.presentation.Navigation

sealed class Route(
    val route : String
){
  object AuthRoute: Route("AuthScreen")
}
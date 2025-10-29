package com.example.firebase_mock

interface MyNavDestination {
    val route: String
}

object MainScreen: MyNavDestination {
    override val route = "main"
}
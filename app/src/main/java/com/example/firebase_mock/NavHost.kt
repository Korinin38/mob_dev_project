package com.example.firebase_mock

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firebase_mock.sign_in.SignInDestination
import com.example.firebase_mock.sign_in.SignInScreen

@Composable
fun MyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = MainScreen.route,
        modifier = modifier
    ) {
        composable(route = MainScreen.route) {
            Greeting(
                name = "",
                onSignIn = { navController.navigate(SignInDestination.route) },
                onSignUp = { navController.navigate(SignInDestination.route) }
            )
        }
        composable(
            route = SignInDestination.route
            ) {
            SignInScreen()
        }
    }
}
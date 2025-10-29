package com.example.firebase_mock.sign_in

import androidx.navigation.navArgument
import com.example.firebase_mock.MyNavDestination

object SignInDestination: MyNavDestination {
    override val route = "sign_in"
    const val accountTypeArg = "accountType"

    val arguments = listOf(
        navArgument(accountTypeArg) {}
    )
}
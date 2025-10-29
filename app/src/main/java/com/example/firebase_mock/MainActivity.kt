package com.example.firebase_mock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.firebase_mock.sign_in.SignInManager
import com.example.firebase_mock.ui.theme.FirebaseMocklicationTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


class MainActivity : ComponentActivity() {
    private val MainActivity.ANONYMOUS: String?
        get() = ""
    private var auth = Firebase.auth

    lateinit var signInManager: SignInManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        if (auth.currentUser == null) {
//            // Not signed in, launch the Sign In activity
//            val res = signInManager.SignInWithGoogle()
//            if (res is AuthResponse.Success) {
//
//            }
//        }


        enableEdgeToEdge()
        setContent {
            FirebaseMocklicationTheme {
                val context = LocalContext.current
                val signInManager = remember { SignInManager(context = context) }
                val coroutineScope = rememberCoroutineScope()
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
//                    Greeting(
//                        name = "str",
//                        onSignIn = {
//                            signInManager.SignInWithGoogle()
//                                .onEach { authResponse ->
//                                    if (authResponse is AuthResponse.Error) {
//                                        Log.w(context.attributionTag, authResponse.message  )
//                                    }
//                                }.launchIn(coroutineScope)
//                        },
//                        onSignUp = {
//                            signInManager.SignInWithGoogle()
//                                .onEach { authResponse ->
//                                    if (authResponse is AuthResponse.Error) {
//                                        Log.w(context.attributionTag, authResponse.message  )
//                                    }
//                                }.launchIn(coroutineScope)
//                        },
//                        modifier = Modifier.padding(innerPadding),
//                    )
                }
            }
        }
    }
//
//    private fun getPhotoUrl(): String? {
//        val user = auth.currentUser
//        return user?.photoUrl?.toString()
//    }

//    private fun getUserName(): String? {
//        return null
////        val user = auth.currentUser
////        return if (user != null) {
////            user.displayName
////        } else ANONYMOUS
//    }

//    private fun signOut() {
//        // TODO
////        AuthUI.getInstance().signOut()
//        finish()
//    }
}

@Composable
fun Greeting(
    name: String,
    onSignIn: () -> Unit,
    onSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(
            onClick = onSignIn,
            modifier = modifier
        ) {
            Text("Sign in")
        }
        Button(
            onClick = onSignIn,
            modifier = modifier
        ) {
            Text("Sign up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseMocklicationTheme {
        Greeting("Android", {}, {})
    }
}
package com.example.firebase_mock

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebase_mock.sign_in.SignInManager
import com.example.firebase_mock.ui.theme.FirebaseMocklicationTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : ComponentActivity() {
    private val MainActivity.ANONYMOUS: String?
        get() = ""
    private lateinit var auth: FirebaseAuth

    lateinit val signInManager : SignInManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        signInManager = SignInManager(context = applicationContext)
        auth = Firebase.auth
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            signInManager.SignInWithGoogle()
//            startActivity(Intent(this, SignInActivity::class.java))
//            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            FirebaseMocklicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in.
        if (auth.currentUser == null) {
            // Not signed in, launch the Sign In activity
            signInManager.SignInWithGoogle()
//            startActivity(Intent(this, SignInActivity::class.java))
//            finish()
            return
        }
    }

    private fun getPhotoUrl(): String? {
        val user = auth.currentUser
        return user?.photoUrl?.toString()
    }

    private fun getUserName(): String? {
        return null
//        val user = auth.currentUser
//        return if (user != null) {
//            user.displayName
//        } else ANONYMOUS
    }

    private fun signOut() {
        // TODO
//        AuthUI.getInstance().signOut()
        finish()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(
            // Crash trigger
            // TODO: remove
//            onClick = { throw RuntimeException("Test Crash") },
            onClick = {  },
            modifier = modifier
        ) {
            Text(
                text = "Crash"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirebaseMocklicationTheme {
        Greeting("Android")
    }
}
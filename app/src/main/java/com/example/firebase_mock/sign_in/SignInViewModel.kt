package com.example.firebase_mock.sign_in

import android.net.Uri
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : ViewModel() {
    var imageUri : Uri = Uri.EMPTY
    var userDisplayName : String = ""
    var signedAsAnonymous : Boolean = false

    fun updateUI(user: FirebaseUser) {
        userDisplayName = user.displayName!!
        imageUri = user.photoUrl!!
        signedAsAnonymous = user.isAnonymous
    }
}
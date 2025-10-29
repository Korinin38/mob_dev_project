package com.example.firebase_mock

import android.content.Context
import androidx.credentials.CredentialManager
import android.os.Bundle
import android.util.Log
import androidx.credentials.GetCredentialRequest
import androidx.activity.ComponentActivity
import androidx.activity.contextaware.ContextAware
import androidx.credentials.Credential
import androidx.credentials.CustomCredential
import com.google.android.gms.games.gamessignin.AuthResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.internal.Logger.TAG
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SignInActivity : ComponentActivity() {
}

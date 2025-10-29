package com.example.firebase_mock.sign_in

import android.content.Context
import android.os.Message
import android.util.Log
import androidx.credentials.CreateCredentialRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.firebase_mock.R
import com.google.android.gms.auth.api.Auth
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.crashlytics.internal.Logger.TAG
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.security.MessageDigest
import java.util.UUID

interface AuthResponse {
    data object Success: AuthResponse
    data class Error(val message: String): AuthResponse
}

class SignInManager(
    private val context: Context
) {
    lateinit var auth: FirebaseAuth

    fun SignInWithGoogle(
        signUp: Boolean = false
    ): Flow<AuthResponse> = callbackFlow {
        auth = Firebase.auth

        val googleIdOption = GetGoogleIdOption.Builder()
            // Your server's client ID, not your Android client ID.
            .setServerClientId(context.getString(R.string.default_web_client_id))
            // Only show accounts previously used to sign in.
            .setFilterByAuthorizedAccounts(true)
            .setNonce(createNonce())
            .build()

        try {
            val credentialManager = CredentialManager.create(context)


//            if(signUp) {
                val credentialRequest = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                    val result =
                        credentialManager.getCredential(
                    context = context,
                    request = credentialRequest
                )
//            } else {
//                val credentialRequest = CreateCredentialRequest
//                    .build()

//                credentialManager.createCredential(context = context, request = credentialRequest)
//            }


            val credential = result.credential

            if (credential is CustomCredential && credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                try {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val credential =
                        GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    auth.signInWithCredential(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("AAAAAA", "signInWithCredential:success")
                                trySend(AuthResponse.Success)
                            } else {
                                Log.w("AAAAAA", "signInWithCredential:failure", task.exception)
                                trySend(AuthResponse.Error(task.exception?.message ?: ""))
                            }
                        }
                } catch (e: GoogleIdTokenParsingException) {
                    trySend(AuthResponse.Error(e.message ?: ""))
                }
            } else {
                Log.w("AAAAAA", "Credential is not of type Google ID!")
            }
            handleSignIn(result.credential)
        } catch (e: Exception) {
            trySend(AuthResponse.Error(e.message ?: ""))
        }
        awaitClose()
    }

    private fun handleSignIn(credential: Credential) {
        // Check if credential is of type Google ID

    }

    private fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)

        return digest.fold("") { str, it ->
            str + "%02x".format(it)
        }
    }
}
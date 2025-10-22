package com.example.firebase_mock.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SignInScreen() {
    Scaffold (modifier = Modifier.padding()) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val viewModel: SignInViewModel = viewModel()
            SignInSubScreen(
                userDisplayName = viewModel.userDisplayName,
                contentPadding = it
            )
        }
    }
}

@Composable
fun SignInSubScreen(
    userDisplayName: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column (
        modifier = modifier.padding(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(userDisplayName)
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}
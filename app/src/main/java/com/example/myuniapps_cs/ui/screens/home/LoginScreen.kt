package com.example.myuniapps_cs.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.TextButtonDefaults
import com.example.myuniapps_cs.ui.auth.loginUser
import com.example.myuniapps_cs.ui.components.molecules.BtnPrimary
import com.example.myuniapps_cs.ui.components.molecules.BtnSecondary
import com.example.myuniapps_cs.ui.components.molecules.InputField
import com.example.myuniapps_cs.ui.theme.MyUniAppsCSTheme


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF34302A),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(16.dp, 16.dp, 16.dp, 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))

        // Email Text Field
        InputField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Student Email..."
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Password Text Field
        InputField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = "Password..."
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Log In Button
        BtnPrimary(
            onClick = {
                isLoading = true
                loginUser(email, password, onSuccess = {
                    isLoading = false
                    onLoginSuccess()
                }, onError = { error ->
                    isLoading = false
                    errorMessage = error
                })
            },
            text = "Log In",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Create account button
        BtnSecondary(
            onClick = {
                /* Navigate to Register Screen */
                },
            text = "Create Account",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password prompt
        TextButton(
            onClick = { /* Navigate to Password reset */ }
        ) { Text(text = "Forgotten Password? Reset", color = MaterialTheme.colorScheme.primary)}

        }
    }


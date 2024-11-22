package com.example.myuniapps_cs.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myuniapps_cs.ui.components.BtnPrimary
import com.example.myuniapps_cs.ui.components.BtnSecondary
import com.example.myuniapps_cs.ui.components.InputField
import com.example.myuniapps_cs.ui.database.FirebaseService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val firebaseService = remember { FirebaseService() }
    var isLoading by remember { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    Box(Modifier.fillMaxSize()
        .padding(16.dp, 100.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF34302A),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(16.dp, 16.dp, 16.dp, 16.dp)
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))

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

            Spacer(modifier = Modifier.height(16.dp))
            // Confirm Password Text Field
            InputField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Confirm Password..."
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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text ="By creating an account, you agree to our Terms of Service and Privacy Policy. " +
                    "Your data will only be used for providing app services, personalizing your experience, " +
                    "and facilitating communication with clubs and societies",
                modifier = Modifier
                )

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            BtnPrimary(
                onClick = {
                    val emailRegex = Regex("^k\\d{8}@student\\.tus\\.ie$")
                    isLoading = true

                    if(!emailRegex.matches(email)){
                        errorMessage = "You must use a Valid TUS Student email address"
                        isLoading = false
                        return@BtnPrimary
                    }
                    // validation
                    if (password != confirmPassword) {
                        errorMessage = "Passwords do not match"
                        isLoading = false
                        return@BtnPrimary
                    } else {
                        // Create user with email and password in Firebase Authentication
                        Firebase.auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                if (task.isSuccessful) {
                                    coroutine.launch {
                                        // Add user to the Real Time Database
                                        firebaseService.addUser(email)
                                    }
                                    // Display success message
                                    errorMessage = "Registration Successful!"
                                    // navigate home
                                    navController.navigate("home")
                                } else {
                                    errorMessage =
                                        task.exception?.localizedMessage ?: "Registration failed"
                                }
                            }

                    }
                },
                text = "Create Account",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Back to log in
            BtnSecondary(
                onClick = {
                    navController.navigate("home")
                },
                text = "Back to Login",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

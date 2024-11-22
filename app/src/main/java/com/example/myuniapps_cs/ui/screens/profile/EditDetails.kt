package com.example.myuniapps_cs.ui.screens.profile

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myuniapps_cs.ui.components.BtnPrimary
import com.example.myuniapps_cs.ui.components.BtnSecondary
import com.example.myuniapps_cs.ui.components.InputField
import com.example.myuniapps_cs.ui.database.FirebaseService
import com.example.myuniapps_cs.ui.database.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun EditDetails(navController: NavController){
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val firebaseService = remember { FirebaseService() }
    val userId = remember { Firebase.auth.currentUser?.uid }
    var user by remember { mutableStateOf<User?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val coroutine = rememberCoroutineScope()

    // On Launch, retrieve the user from the db
    LaunchedEffect(userId) {
        userId?.let {
            isLoading = true
            user = firebaseService.getUser(userId)
            isLoading = false
        }
    }

    Box(
        Modifier.fillMaxSize()
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
            user?.displayName?.let {
                InputField(
                    value = it,
                    onValueChange = { user = user?.copy(displayName = it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Display Name"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Password Text Field
            user?.courseTitle?.let {
                InputField(
                    value = it,
                    onValueChange = { user = user?.copy(courseTitle = it) },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Password..."
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Your data will only be used for providing app services, personalizing your experience, " +
                        "and facilitating communication with clubs and societies",
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            BtnPrimary(
                onClick = {
                    coroutine.launch {
                        // Update User
                        isLoading = true
                        user?.let { firebaseService.updateUser(it) }
                        isLoading = false
                    }
                    navController.navigate("profile")
                },
                text = "Save Details",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Back to log in
            BtnSecondary(
                onClick = {
                    navController.navigate("profile")
                },
                text = "Cancel",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}
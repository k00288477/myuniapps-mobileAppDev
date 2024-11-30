package com.example.myuniapps_cs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myuniapps_cs.ui.components.BtnDanger
import com.example.myuniapps_cs.ui.components.BtnSecondary
import com.example.myuniapps_cs.ui.database.FirebaseService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    val firebaseService = FirebaseService()
    val userId = remember { Firebase.auth.currentUser?.uid ?: null }
    var showResetPassword by remember { mutableStateOf(false) }
    var showDeleteAccount by remember { mutableStateOf(false) }
    var isDarkTheme by rememberSaveable { mutableStateOf(true) }
    var showConfirmationMessage by rememberSaveable { mutableStateOf(false) }
    // Main settings screen composable

    Column (
        modifier = Modifier.padding(16.dp, 92.dp).fillMaxHeight()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF34302A),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(8.dp, 32.dp, 8.dp, 16.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.Start)
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Slider for theme switching
            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "Theme",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
                // slider

//                Slider(
//                    modifier = Modifier.align(Alignment.End).padding(end = 8.dp),
//                    value = isDarkTheme,
//                    onValueChange = { value -> isDarkTheme}
//                )

            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                thickness = 1.dp, // Set the thickness of the horizontal rule
                color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
            )
            // Reset Password
            // Navigate to Reset password screen
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showResetPassword = !showResetPassword
                }) {
                Text(
                    text = "Reset Password",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                thickness = 1.dp, // Set the thickness of the horizontal rule
                color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
            )
            if(showResetPassword){
                // Show reset password
                BtnDanger(
                    text = "Reset Password",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        coroutineScope.launch {
                            val email = Firebase.auth.currentUser?.email
                            if (email != null) {
                               Firebase.auth.sendPasswordResetEmail(email)
                                showConfirmationMessage = true
                            } else {
                                navController.navigate("settings")
                            }
                        }
                    }

                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                    thickness = 1.dp, // Set the thickness of the horizontal rule
                    color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
                )
            }



            Spacer(modifier = Modifier.weight(1f))
            // Delete Account
            // Prompt user to confirm
            TextButton(
                modifier = Modifier.align(Alignment.Start),
                onClick = {
                    showDeleteAccount = !showDeleteAccount
                }) {
                Text(
                    text = "Delete Account",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                    )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                thickness = 1.dp, // Set the thickness of the horizontal rule
                color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
            )
            if(showDeleteAccount) {
                BtnDanger(
                    text = "Delete Account",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        coroutineScope.launch {
                            // Delete User Account
                            firebaseService.deleteUserAccount(userId)
                            // Delete user from Authentication
                            var user = Firebase.auth.currentUser
                            user?.delete()

                            // Navigate to home screen
                            navController.navigate("home")
                        }
                    }
                )
            }

    if(showConfirmationMessage) {
        // Confirmation message
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF34302A),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(8.dp, 32.dp, 8.dp, 16.dp)

        ) {
            Text(
                text = "Reset password email sent",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.inverseSurface,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            BtnSecondary(
                text = "Dismiss",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showConfirmationMessage = false
                }
            )
        }// end of message
    }


            }
    } // End of column
}

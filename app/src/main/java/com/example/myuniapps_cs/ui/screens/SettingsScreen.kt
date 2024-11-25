package com.example.myuniapps_cs.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myuniapps_cs.ui.components.BtnDanger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController){
    val coroutineScope = rememberCoroutineScope()
    var showResetPassword by remember { mutableStateOf(false) }
    var showDeleteAccount by remember { mutableStateOf(false) }

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
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Slider for theme switching
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Theme",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.SemiBold
                )

            }
            // Reset Password
            // Navigate to Reset password screen
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showResetPassword = true
                }) {
                Text(
                    text = "Reset Password",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontWeight = FontWeight.SemiBold
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

                    }
                )
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                    thickness = 1.dp, // Set the thickness of the horizontal rule
                    color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
                )
            }



            Spacer(modifier = Modifier.weight(1f))
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                thickness = 1.dp, // Set the thickness of the horizontal rule
                color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
            )
            // Delete Account
            // Prompt user to confirm
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showDeleteAccount = true
                }) {
                Text(
                    text = "Clubs & Societies",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.errorContainer,
                    fontWeight = FontWeight.SemiBold)
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                thickness = 1.dp, // Set the thickness of the horizontal rule
                color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
            )


            }
    } // End of column
}
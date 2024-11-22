package com.example.myuniapps_cs.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myuniapps_cs.R
import com.example.myuniapps_cs.ui.components.BtnSecondary
import com.example.myuniapps_cs.ui.components.InputField
import com.example.myuniapps_cs.ui.database.FirebaseService
import com.example.myuniapps_cs.ui.database.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfileDetails(navController: NavController){
    val firebaseService = remember { FirebaseService() }
    val userId = remember { Firebase.auth.currentUser?.uid }
    var user by remember { mutableStateOf<User?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    // On Launch, retrieve the user from the db
    LaunchedEffect(userId) {
        userId?.let {
            isLoading = true
            user = firebaseService.getUser(userId)
            isLoading = false
        }
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF34302A),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(16.dp, 32.dp, 16.dp, 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            Text(
                text = "My Profile",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 17.dp)
                ) {
                    Text(
                        text = user?.displayName ?: "",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Text(
                        text = user?.email ?: "",
                        color = Color.White,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 11.dp)
                    )
                }
            } // End Row
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
                thickness = 1.dp, // Set the thickness of the horizontal rule
                color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user?.courseTitle ?: "",
                color = Color.White,
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Edit Button
            BtnSecondary(
                onClick = {
                    navController.navigate("editProfile")
                },
                text = "Edit Details",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

        } // End Column
    }


}



package com.example.myuniapps_cs.ui.screens.clubs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myuniapps_cs.R
import com.example.myuniapps_cs.ui.components.LoadingWheel
import com.example.myuniapps_cs.ui.database.Club
import com.example.myuniapps_cs.ui.database.FirebaseService
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.model.cameraPosition
import kotlinx.coroutines.launch


@Composable
fun ClubDrillDown(navController: NavController, clubId: String?) {
    val firebaseService = FirebaseService()
    val coroutine = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var club by remember { mutableStateOf<Club?>(null) }

    LaunchedEffect(clubId) {
        coroutine.launch {
            if(!clubId.isNullOrEmpty()) {
                // get club
                try {
                    club = firebaseService.getClub(clubId ?: "")
                    isLoading = false
                } catch (e: Exception) {
                    errorMessage = "Failed to load club details: ${e.message}"
                    isLoading = false
                }
            } else {
                errorMessage = "Club Id is invalid"
                isLoading = false
            }
        }
    }

    Column(
        modifier = Modifier.padding(16.dp, 92.dp)
    ) {
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
                text = "Club Details",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(8.dp, 6.dp)
            )

            if(club != null) {
                if (isLoading) {
                    // Display Loading wheel during load
                    LoadingWheel()
                } else {
                    // Drill down details
                    ClubDrillDownDetails(club!!)
                }
            } else {
                Text("Error fetching club details...")
            }

            }
        }

    }

@Composable
fun ClubDrillDownDetails(club: Club) {
    val context = LocalContext.current
    val imageName = club.thumbnail
    val imageResId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
    val defaultLocation = LatLng(52.675400, -8.64860)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 16f)
    }
    Column {
        Row(
            modifier = Modifier
                .padding(top = 0.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Club Profile Img",
                modifier = Modifier
                    .size(62.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = club.clubName,
                color = Color.White,
                fontSize = 24.sp
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp), // Add vertical spacing around the divider
            thickness = 1.dp, // Set the thickness of the horizontal rule
            color = MaterialTheme.colorScheme.surfaceVariant // Customize the color
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = club.description,
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Next Meeting: ",
            color = Color.White,
            fontSize = 16.sp
        )
        GoogleMap(
            modifier = Modifier.height(350.dp),
            cameraPositionState = cameraPositionState
        )

    }
}




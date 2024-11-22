package com.example.myuniapps_cs.ui.screens.clubs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myuniapps_cs.R
import com.example.myuniapps_cs.ui.components.BtnSecondary
import com.example.myuniapps_cs.ui.components.BtnTertiary
import com.example.myuniapps_cs.ui.database.Club
import com.example.myuniapps_cs.ui.database.FirebaseService
import kotlinx.coroutines.launch

@Composable
fun ClubsScreen(navController: NavController){
    val firebaseService = FirebaseService()
    val coroutine = rememberCoroutineScope()
    var clubs by remember { mutableStateOf<List<Club>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        coroutine.launch {
            // get all clubs
            try {
                clubs = firebaseService.getAllClubs()
                isLoading = false
            } catch (e: Exception) {
                errorMessage = "Failed to load clubs: ${e.message}"
                isLoading = false
            }
        }
    }

    Column (
        modifier = Modifier.padding(16.dp, 92.dp)
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
                text = "Clubs & Societies",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            if (clubs != null) {
                for (club in clubs!!) {
                    ClubDetails(club, navController)
                    Spacer(modifier = Modifier.height(4.dp))
                }
            } else {
                    Text(
                        text = "Oops, theres nothing here :("
                    )
                }
            }
        }
    }

@Composable
fun ClubDetails(club: Club, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = "TUS Badminton Logo",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = club.clubName,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.weight(1f))
        BtnTertiary(
            onClick = {
                navController.navigate("")
            },
            text = "Details",
            modifier = Modifier
                .width(104.dp)
                .padding(8.dp, 4.dp)
        )

    }

}

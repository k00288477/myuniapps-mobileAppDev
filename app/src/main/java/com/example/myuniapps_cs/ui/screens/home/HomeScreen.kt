package com.example.myuniapps_cs.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myuniapps_cs.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomeScreen(navController: NavHostController){
    var isUserLoggedIn by remember { mutableStateOf(Firebase.auth.currentUser != null) }

    Box (modifier = Modifier.fillMaxSize()) {
        // Home Image and Text Blurb
        HomeImage(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(top = 50.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Text(
                text = "Discover and connect with campus clubs and societies, " +
                        "stay updated on events, and easily manage your " +
                        "favorites – create an account today to get started!",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 8.dp)
                    .fillMaxWidth()
                    .background(color = Color.Black.copy(alpha = 0.50f))
                    .offset(y = 16.dp)
            )
        }

        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp, 450.dp, 16.dp, 16.dp)
        ) {
            // Log In section
            if (isUserLoggedIn) {
                LoggedIn()
            } else {
                LoginScreen(onLoginSuccess = {
                    isUserLoggedIn = true;
                })
            }
        }
    }
}



@Composable
fun HomeImage(modifier: Modifier){
    Image(
        painter = painterResource(R.drawable.home_img),
        contentDescription = "TUS sign on wall",
        modifier = modifier
    )
}
package com.example.myuniapps_cs.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myuniapps_cs.ui.components.BtnSecondary

@Composable
fun ProfileFavourites(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF34302A),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(16.dp, 32.dp, 16.dp, 16.dp)
    ) {
        Column (
            modifier = Modifier.padding(bottom = 16.dp)
        ){
            Text(
                text = "Favourite Clubs",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Oops... Theres nothing to show\nClick below to find some you like!"
            )
            Spacer(modifier = Modifier.height(16.dp))
            BtnSecondary(
                text = "View Favourites",
                onClick = { navController to "clubs" },
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

fun getUserFavourites(){
    // Retrieve User Favourite Clubs from DB
}
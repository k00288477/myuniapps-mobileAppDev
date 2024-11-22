package com.example.myuniapps_cs.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController){
    Column (
        modifier = Modifier.padding(16.dp, 92.dp)
    ){
        ProfileDetails(navController)

        Spacer(modifier = Modifier.height(16.dp))

        ProfileFavourites(navController)

    }


}
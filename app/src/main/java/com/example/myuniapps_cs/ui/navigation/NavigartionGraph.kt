package com.example.myuniapps_cs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myuniapps_cs.ui.screens.home.HomeScreen
import com.example.myuniapps_cs.ui.screens.profile.ProfileScreen


@Composable
fun NavigationGraph( navController: NavHostController ) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }


    }
}
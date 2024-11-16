package com.example.myuniapps_cs.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myuniapps_cs.Logo
import com.example.myuniapps_cs.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

// define UI for the drawer menu
@Composable
fun DrawerContent( navController: NavHostController, drawerState: DrawerState ){
    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxHeight()
            .width(250.dp)
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        LogoTUS()
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text ="Navigation", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.padding(8.dp))

        // Home Item
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { coroutineScope.launch { // button is clicked
                drawerState.close() // close the drawer
                navController.navigate("home") // navigate to home screen
            }
            }) {
            Text(
                text = "Home",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontWeight = FontWeight.SemiBold)
        }

        // Profile item
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { coroutineScope.launch { // button is clicked
                drawerState.close() // close the drawer
                navController.navigate("profile") // navigate to Profile screen
            }
            }) {
            Text(
                text = "Profile",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Firebase.auth.signOut() // sign user out
                navController.navigate("home") // navigate to the home page
            }
            ) {
            Text(
                text = "Log Out",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.errorContainer,
                fontWeight = FontWeight.SemiBold)
        }

    } // end of column`
} // end of composable

@Composable
fun LogoTUS() {
    Image(
        painter = painterResource(R.drawable.logo_tus),
        contentDescription = "TUS logo",
        modifier = Modifier.fillMaxWidth()
    )
}


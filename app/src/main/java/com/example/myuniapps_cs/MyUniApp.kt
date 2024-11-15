package com.example.myuniapps_cs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myuniapps_cs.ui.navigation.DrawerContent
import com.example.myuniapps_cs.ui.navigation.NavigationGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MyUniApp() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController, drawerState = drawerState)
        },
        modifier = Modifier

    ) {
        Scaffold(
            topBar = { MyTopBar(drawerState, coroutineScope) },
            content = { paddingValues -> MainContent(navController, paddingValues) }
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(drawerState: DrawerState, coroutineScope: CoroutineScope){
    TopAppBar(
        title = { Text(
            text = "My Uni Apps - Clubs & Societies",
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.background(Color.Black)
        ) },
        navigationIcon = {
            IconButton(onClick = {
                toggleDrawer(drawerState, coroutineScope)
            }) { Icon( Icons.Filled.MoreVert, contentDescription = "Menu Icon") }
        }
    )
}

@Composable
fun MainContent(navController: NavHostController, paddingValues: PaddingValues){
    NavigationGraph(navController = navController)
}
// function to toggle the state of the drawer menu
private fun toggleDrawer( drawerState: DrawerState, coroutineScope: CoroutineScope ){
    coroutineScope.launch {
        if(drawerState.isClosed) drawerState.open() else drawerState.close();
    }
}




package com.example.myuniapps_cs.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myuniapps_cs.ui.components.molecules.BtnPrimary
import com.example.myuniapps_cs.ui.components.molecules.BtnSecondary

@Composable
fun LoggedIn(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF34302A),
                shape = RoundedCornerShape(30.dp)
            )
            .padding(64.dp)
    ){
        BtnPrimary(
            text = "View All Clubs",
            onClick = { /* Navigate to Clubs Screen */ },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp).fillMaxWidth())
        BtnSecondary(
            text = "View Favourites",
            onClick = { /* Navigate to Profile Favourites */ },
            modifier = Modifier.fillMaxWidth()
        )

    }


}
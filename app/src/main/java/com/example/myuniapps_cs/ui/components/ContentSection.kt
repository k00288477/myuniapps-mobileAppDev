package com.example.myuniapps_cs.ui.components
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentSection() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(
                    color = Color(0xFF34302A),
                    shape = RoundedCornerShape(30.dp)
                )
                .padding(start = 38.dp, top = 39.dp, end = 38.dp, bottom = 218.dp)
        ) {
            Text(
                text = "Heading",
                color = Color(0xFFDFB969),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = androidx.compose.ui.text.font.FontFamily.Default
            )
        }
    }

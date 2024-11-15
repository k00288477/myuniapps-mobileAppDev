package com.example.myuniapps_cs.ui.components.molecules

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define Input fields
@Composable
fun InputField(value: String, onValueChange: (String)-> Unit, modifier: Modifier){

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 14.dp)
            .border(
                width = 2.dp, color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp)
            ),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            textDirection = TextDirection.Ltr,
            lineBreak = LineBreak.Simple,
            hyphens = Hyphens.Auto
    ),

    )
}

@Composable
@Preview
fun Preview(){
    InputField(
        value = "Email",
        onValueChange = { },
        modifier = Modifier.padding(8.dp)
    )
}

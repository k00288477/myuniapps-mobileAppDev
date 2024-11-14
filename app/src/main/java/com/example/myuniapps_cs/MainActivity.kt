package com.example.myuniapps_cs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myuniapps_cs.ui.theme.MyUniAppsCSTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase
        auth = Firebase.auth

        setContent {
            MyUniAppsCSTheme {
                    MyUniApp()
                }
            }
        }
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyUniAppsCSTheme {
    }
}
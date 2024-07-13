package com.example.messageclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.messageclient.presentation.ConnectServerView
import com.example.messageclient.presentation.ui.theme.MessageClientTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessageClientTheme {
                ConnectServerView()
            }
        }
    }
}

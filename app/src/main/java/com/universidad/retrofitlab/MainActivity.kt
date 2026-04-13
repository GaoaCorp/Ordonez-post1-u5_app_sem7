package com.universidad.retrofitlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.universidad.retrofitlab.presentation.ui.PostsScreen
import com.universidad.retrofitlab.ui.theme.RetrofitLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitLabTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    PostsScreen()
                }
            }
        }
    }
}
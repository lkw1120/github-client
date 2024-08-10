package com.lkw1120.client.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lkw1120.client.ui.navigation.NavGraph
import com.lkw1120.client.ui.navigation.NavScreen
import com.lkw1120.client.ui.theme.GithubClientTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GithubClientTheme {

                val navController = rememberNavController()
                val startDestination = NavScreen.SearchScreen.route

                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = startDestination,
                    )
                }
            }
        }
    }
}
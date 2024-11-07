package com.example.guess_the_number

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guess_the_number.ui.screens.EndScreen
import com.example.guess_the_number.ui.screens.GameScreen
import com.example.guess_the_number.ui.screens.HomeScreen
import com.example.guess_the_number.ui.screens.HowToPlayScreen
import com.example.guess_the_number.ui.theme.GuessthenumberTheme
import com.example.guess_the_number.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessthenumberTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Purple80,
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable(route = "home") {
            HomeScreen(navController)
        }

        composable(route = "howtoplay") {
            HowToPlayScreen(navController)
        }

        composable(route = "game") {
            GameScreen(navController)
        }

        composable(route = "end") {
            EndScreen(navController)
        }

    }
}



@Preview(showBackground = true)
@Composable
fun GuessTheNumberPreview() {
    GuessthenumberTheme {
        val navController = rememberNavController()
        // HowToPlayScreen(navController)
        //App()
        GameScreen(navController)
        // EndScreen(navController)
    }
}
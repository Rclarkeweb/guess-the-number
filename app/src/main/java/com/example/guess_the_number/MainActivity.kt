package com.example.guess_the_number

import GameScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.guess_the_number.ui.screens.EndScreen
import com.example.guess_the_number.ui.screens.HomeScreen
import com.example.guess_the_number.ui.screens.HowToPlayScreen
import com.example.guess_the_number.ui.theme.GuessthenumberTheme
import com.example.guess_the_number.ui.theme.Purple80
import com.example.guess_the_number.viewmodel.GameViewModel

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
fun App(
    gameViewModel: GameViewModel = viewModel() // Create GameViewModel instance
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
// pass gameView to all screens updated to use it
        composable(route = "home") {
            HomeScreen(navController, gameViewModel)
        }

        composable(route = "howtoplay") {
            HowToPlayScreen(navController)
        }

        composable(route = "game") {
            GameScreen(navController, gameViewModel)
        }

        composable(route = "end") {
            EndScreen(navController, gameViewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuessTheNumberPreview() {
    GuessthenumberTheme {
        val navController = rememberNavController()
        val gameViewModel: GameViewModel = viewModel()
        // GameScreen(navController, gameViewModel)
        //HomeScreen(navController, gameViewModel)
        // HowToPlayScreen(navController)
    }
}

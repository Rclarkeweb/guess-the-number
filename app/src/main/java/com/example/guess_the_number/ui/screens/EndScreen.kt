package com.example.guess_the_number.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.GuessResultComponent
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80
import com.example.guess_the_number.viewmodel.GameViewModel

@Composable
fun EndScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel(), // Use GameViewModel
) {
    // Access values from the ViewModel
    val guessesLeft by gameViewModel.guessesLeft
    val randomInt by gameViewModel.randomInt

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .safeDrawingPadding()
            .fillMaxHeight()
            .background(Purple80),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display message based on guesses left
        Text(
            text = if (guessesLeft > 0) {
                "You managed to guess with \n$guessesLeft guesses left"
            } else {
                "Better luck next time!"
            },
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 40.sp,
                color = Purple40,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(bottom = 30.dp)
        )

        Text(
            text = "The number was",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                color = Purple40,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
        )

        // Display the random number
        GuessResultComponent(randomInt.toString(), 200)

        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonComponent(
                onClick = {
                    gameViewModel.resetGame() // Reset the game when "Play again" is clicked
                    navController.navigate("game") // Navigate to the game screen
                },
                label = "Play again",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

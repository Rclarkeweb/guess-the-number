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
    gameViewModel: GameViewModel = viewModel()
) {
    val guessesLeft by gameViewModel.guessesLeft
    val correctAnswer = if (gameViewModel.gameMode.value == "city") {
        gameViewModel.correctCity.value.name
    } else {
        gameViewModel.randomInt.value.toString()
    }

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
        Text(
            text = if (guessesLeft > 0) {
                "Well done! You guessed correctly with $guessesLeft guesses left."
            } else {
                "You didn't guess correctly. Better luck next time!"
            },
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 40.sp,
                color = Purple40,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 30.dp)
        )

        Text(
            text = if (gameViewModel.gameMode.value == "city") {
                "The correct city was"
            } else {
                "The correct number was"
            },
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 20.sp,
                color = Purple40,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 10.dp)
        )

        GuessResultComponent(correctAnswer.toString(), 200)

        ButtonComponent(
            onClick = {
                navController.navigate("home")
                gameViewModel.resetGame()
            },
            label = "Play Again",
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp)
        )
    }
}

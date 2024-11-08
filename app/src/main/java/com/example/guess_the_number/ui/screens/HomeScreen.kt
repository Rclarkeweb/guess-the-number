package com.example.guess_the_number.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.DifficultyLevelComponent
import com.example.guess_the_number.ui.components.MainHeadingComponent
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80
import com.example.guess_the_number.viewmodel.GameViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel(), // Use GameViewModel here
) {
    val difficultyLevel by gameViewModel.difficultyLevel // difficulty level state

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
        MainHeadingComponent("Can you guess the number?")

        // CITIES Button
        // Button currently sets gameMode to "city", but otherwise functions like "Start Game" button
//        ButtonComponent(
//            onClick = {
//                gameViewModel.gameMode.value = "city"
//                gameViewModel.resetGame() // needs changing?
//                navController.navigate("game") // needs changing
//            },
//            label = "Guess the capital city?",
//            modifier = Modifier
//                .padding(horizontal = 10.dp)
//        )

        // Difficulty Selection Box
        Text(
            text = "Select Difficulty Level",
            color = Purple40,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Display difficulty level RadioButtons
        DifficultyLevelComponent(
            selectedLevel = gameViewModel.difficultyLevel.value, // pass current difficultyLevel state value
            // when user clicks a radio button that button is shown as selected and the state is updated
            onLevelSelected = { userChosenLevel ->
                gameViewModel.difficultyLevel.value = userChosenLevel
            }
        )


        // OLD Level buttons
//        Row(
//            modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            ButtonComponent(
//                onClick = { gameViewModel.difficultyLevel.value = 0 },
//                label = "Easy",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//            ButtonComponent(
//                onClick = { gameViewModel.difficultyLevel.value = 1 },
//                label = "Medium",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//            ButtonComponent(
//                onClick = { gameViewModel.difficultyLevel.value = 2 },
//                label = "Hard",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//        }
    }

    // Start game and How to Play Buttons
    Column(
            modifier = Modifier.padding(top = 550.dp, bottom = 10.dp)
                .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ButtonComponent(
                onClick = {
                    gameViewModel.resetGame() // Reset game state with selected difficulty
                    navController.navigate("game")
                },
                label = "Start Game",
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )

            ButtonComponent(
                onClick = { navController.navigate("howtoplay") },
                label = "How to play",
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )

        }
    }



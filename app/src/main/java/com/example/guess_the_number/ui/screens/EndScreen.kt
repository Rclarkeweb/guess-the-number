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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.GuessResultComponent
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80

@Composable
fun EndScreen(
    navController: NavController,
    guessesLeft: Int,
    modifier: Modifier = Modifier,
) {
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
                "You managed to guess with \n$guessesLeft guesses left" // Displays guesses left
            } else {
                "Better luck next time!" // Displays if 0 guesses left
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

        // Display actual number and gradient circle
        GuessResultComponent("12", 200)

//        Text(
//            text = if (guessesLeft > 0) {
//                "You managed to guess with \n$guessesLeft guesses left",
//                textAlign = TextAlign.Center,
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    color = Purple40,
//                    fontWeight = FontWeight.Bold
//                ),
//                modifier = Modifier
//                    .padding(top = 20.dp, bottom = 30.dp)
//            }
//            else {
//                "Better luck next time!,
//                textAlign = TextAlign.Center,
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    color = Purple40,
//                    fontWeight = FontWeight.Bold
//                ),
//                modifier = Modifier
//                    .padding(top = 20.dp, bottom = 30.dp)
//            }
//        )

        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonComponent(
                onClick = { navController.navigate("home") },
                label = "Play again",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}
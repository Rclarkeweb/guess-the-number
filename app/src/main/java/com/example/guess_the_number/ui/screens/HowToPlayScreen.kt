package com.example.guess_the_number.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.MainHeadingComponent
import com.example.guess_the_number.ui.theme.Purple80

@Composable
fun HowToPlayScreen(
    navController: NavController, // needed to change screens
    modifier: Modifier = Modifier
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
        // Display heading
        MainHeadingComponent("How to play")

        // Guidance on how to play
        Text(
            text = "Pick a difficulty level: Easy, Medium or Hard",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Text(
            text = "You will guess a number between a randomised range:\nEasy is 1 - 30\nMedium is 1 - 50\nHard is 1 - 100",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Your guess will either be:\n" +
                    "- Correct\n" +
                    "- Too low\n" +
                    "- Too high\n",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Text(
            text = "You only have 5 guesses to get the number right",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Good luck!",
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .padding(bottom = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left
        )

        // Button to go back to home - HomeScreen()
        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonComponent(
                onClick = { navController.navigate("home") },
                label = "Back to home",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}
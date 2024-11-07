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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.FinishGameButtonComponent
import com.example.guess_the_number.ui.components.GuessResultComponent
import com.example.guess_the_number.ui.components.GuessesLeftComponent
import com.example.guess_the_number.ui.components.TextFieldComponent
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80
import kotlin.random.Random

@Composable
fun GameScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    // Users Number Guess
    var usersGuess by remember { mutableStateOf("") }

    var result by remember { mutableStateOf(0) }

    // Guesses left saved to state - this number will decrease when a user makes a guess
    var guessesLeft by remember { mutableStateOf(5) }

    val levelHighestNumber = 31

    // Generate a random number and save it to state (1 inclusive and 30 exclusive (31))
    val randomInt by remember { mutableStateOf(Random.nextInt(1, levelHighestNumber)) }
    println(randomInt)

    // Check if all guesses have been used
    if (guessesLeft <= 0) {
        navController.navigate("end/$guessesLeft") // goes to end screen when guesses left below zero, passes guesses left value to next screen
    }
    // Check if guess is correct
    if (result.toInt() == randomInt) {
        navController.navigate("end/$guessesLeft") // goes to end screen when guesses left below zero, passes guesses left value to next screen
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

        // Display the number of guesses left
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End // align to the right of the screen
        ) {
            GuessesLeftComponent(guessesLeft)
        }

        // Guess a number text - displays range
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Guess a number between\n"
                        + "1 and 30",
                style = TextStyle(
                    fontSize = 24.sp,
                    color = Purple40,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

        // Input guess text field and button
        Column(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldComponent(
                value = usersGuess, // current value of the input text field
                onValueChange = { usersGuess = it }, // save value typed to usersGuess
                modifier = Modifier
            )

            // Display a message is input field is empty and number is invalid
            if (usersGuess.isEmpty() || (usersGuess.toIntOrNull() != null && usersGuess.toInt() > (levelHighestNumber - 1))) {
                Text(
                    text = if (usersGuess.isEmpty()) {
                        "Please enter a number"
                    } else {
                        "Please only guess a number lower than ${levelHighestNumber - 1}"
                    },
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Purple40,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(bottom = 20.dp, top = 10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {
                // Display the Submit guess button
                // Execute the isGuessCorrect function
                ButtonComponent(
                    onClick = { // logic changed
                        result = usersGuess.toInt()
                        println(result)
                        println(randomInt)
                        guessesLeft -= 1
                    },
                    label = "Submit Guess!",
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }

        // Display Guess Result circle
        Row(
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
        ) {

            GuessResultComponent("Guess again... ", 400)
        }

        // Finish Game Button
        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FinishGameButtonComponent(
                onClick = {  navController.navigate("home") },
                label = "Finish game",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }

    }
}
// Not called
fun isGuessCorrect(randomNum: Int, guess: Int): String {
    return when {
        randomNum == guess -> "Correct"
        randomNum > guess -> "Too low"
        randomNum < guess -> "Too high"
        else -> "Problem!"
    }
}


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.FinishGameButtonComponent
import com.example.guess_the_number.ui.components.GuessResultComponent
import com.example.guess_the_number.ui.components.GuessesLeftComponent
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80
import com.example.guess_the_number.viewmodel.GameViewModel
import kotlin.random.Random
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel() // Get ViewModel instance
) {

    val usersGuess by gameViewModel.usersGuess
    val guessesLeft by gameViewModel.guessesLeft
    val randomInt by gameViewModel.randomInt
    val difficultyLevel by gameViewModel.difficultyLevel
    val max = gameViewModel.max


    var guessResult by remember { mutableStateOf("Make a guess...")}


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
                text = "Guess a number between\n1 and ${max - 1}",
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
            TextField(
                value = usersGuess,
                onValueChange = { input ->
                    if (input.all { it.isDigit() }) {
                        gameViewModel.usersGuess.value = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
            )

            if (usersGuess.isEmpty() || (usersGuess.toIntOrNull() != null && usersGuess.toInt() > max -1)) {
                Text(
                    text = if (usersGuess.isEmpty()) {
                        "Please enter a number"
                    } else {
                        "Please only guess a number between 0 and ${max - 1}"
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
                // Execute the isGuessCorrect function to check the users guess
                ButtonComponent(
                    onClick = {
                        gameViewModel.submitGuess()
                        guessResult = isGuessCorrect(randomInt, usersGuess.toInt())
                        if (usersGuess.toInt() == randomInt) {
                          Handler(Looper.getMainLooper()).postDelayed({
                              navController.navigate("end") // goes to end screen when guess is equal to the actual number
                          }, 2000)
                        }
                        if (guessesLeft <= 0) {
                            navController.navigate("end")
                        }
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
            GuessResultComponent(guessResult, 400)

        }

        // Finish Game Button
        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            FinishGameButtonComponent(
                onClick = { navController.navigate("home") },
                label = "Finish game",
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
        }
    }
}

// Logic to check if number is correct or incorrect. Returns a message to the user
fun isGuessCorrect(randomNum: Int, guess: Int): String {
    return when {
        randomNum == guess -> "Correct!"
        randomNum > guess -> "Too low\nGuess again..."
        randomNum < guess -> "Too high\nGuess again..."
        else -> "Problem!"
    }
}


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
fun CityGuesser(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel()
) {
    var usersGuess by gameViewModel.usersGuess
    val guessesLeft by gameViewModel.guessesLeft
    val guessResult by gameViewModel.guessResult
    val gameOver by gameViewModel.gameOver

    if (gameOver) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate("end")
        }, 2000)
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
            horizontalArrangement = Arrangement.End
        ) {
            GuessesLeftComponent(guessesLeft)
        }

        // City guess prompt
        Text(
            text = "Guess the correct city!",
            style = TextStyle(
                fontSize = 24.sp,
                color = Purple40,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )

        // Input for city guess
        TextField(
            value = usersGuess,
            onValueChange = { gameViewModel.usersGuess.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Your guess") }
        )

        // Submit button
        ButtonComponent(
            onClick = { gameViewModel.submitGuess()
                usersGuess = ""},
            label = "Submit Guess",
            modifier = Modifier.padding(top = 10.dp)
        )

        // Display guess result
        GuessResultComponent(guessResult, 400)

        // Finish Game Button
        FinishGameButtonComponent(
            onClick = { navController.navigate("home") },
            label = "Finish game",
            modifier = Modifier.padding(top = 40.dp)
        )
    }
}

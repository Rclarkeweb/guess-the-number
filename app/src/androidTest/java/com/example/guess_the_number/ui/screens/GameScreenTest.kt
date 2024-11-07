package com.example.guess_the_number.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavController
import com.example.guess_the_number.viewmodel.GameViewModel
import io.mockk.mockk
import io.mockk.every
import io.mockk.verify
import org.junit.Rule
import org.junit.Test

import GameScreen
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider


class GameScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSubmitGuessButton() {
        // Create a TestNavHostController instead of mocking NavController
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        // Initialize ViewModel with desired state
        val gameViewModel = GameViewModel().apply {
            usersGuess.value = "15" // Assume the user's guess is 15
            guessesLeft.value = 3   // Assume the user has 3 guesses left
            randomInt.value = 10    // Assume the target number is 10 for testing
        }

        // Set up the GameScreen Composable in the test environment
        composeTestRule.setContent {
            GameScreen(navController = navController, gameViewModel = gameViewModel)
        }

        // Assert that the initial UI elements are displayed
        composeTestRule.onNodeWithText("Submit Guess!").assertExists()

        // Perform click on the "Submit Guess" button
        composeTestRule.onNodeWithText("Submit Guess!").performClick()

        // Check if guessesLeft or navigation would be called
        assert(gameViewModel.guessesLeft.value == 2) // Guesses should decrement by 1

        // Verify that navigation occurs if the game condition triggers it
        if (gameViewModel.usersGuess.value.toInt() == gameViewModel.randomInt.value) {
            // Here we don't need to verify directly as we're using TestNavHostController
            // You can verify navigation through the currentBackStackEntry or similar mechanisms
        }
    }
}

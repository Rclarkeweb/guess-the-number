package com.example.guess_the_number.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.guess_the_number.cities
import com.example.guess_the_number.ui.components.City
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.random.Random

class GameViewModel(application: Application) : AndroidViewModel(application) {

    // Create all the state we're passing around
    var usersGuess = mutableStateOf("")
    var guessesLeft = mutableStateOf(5)

    var difficultyLevel = mutableStateOf(  0) // state for difficulty, as of yet unimplemented

    var gameMode = mutableStateOf("number") // "number" or "city"

    // List to store city objects

    var correctCity = mutableStateOf(cities.random())

    var hintMessage = mutableStateOf("")

    fun revealHint() {
        hintMessage.value = when (guessesLeft.value) {
            3 -> "The population is ${correctCity.value.population}"
            2 -> "It is in the ${correctCity.value.timezone} timezone"
            1 -> "It is in ${correctCity.value.country}"
            else -> ""
        }
    }

    // Moving state from GameScreen

    var guessResult = mutableStateOf("Make a guess...")
    var gameOver = mutableStateOf(false)

    // Determine max value for random number generator
    val max: Int
        get() = when (difficultyLevel.value) {
            0 -> 31 // Easy
            1 -> 51 // Medium
            2 -> 101 // Hard
            else -> 301 // Default to 301
        }

    var randomInt = mutableStateOf(generateRandomNumber())
    // Generate a random number based on difficulty
    private fun generateRandomNumber() = Random.nextInt(1, max) // consider making private? I don't fully know what that does...

    // Moved from GameScreen
    private fun isGuessCorrect(randomNum: Int, guess: Int): String {
        return when {
            randomNum == guess -> "Correct!"
            randomNum > guess -> "Too low\nGuess again..."
            randomNum < guess -> "Too high\nGuess again..."
            else -> "Problem!"
        }
    }


    // Submit a guess for number or city mode
    fun submitGuess() {
        if (gameMode.value == "number") {
            val guess = usersGuess.value.toIntOrNull()

            if (guess == null || guess !in 1 until max) {
                guessResult.value = "Please only guess a number between 1 and ${max - 1}"
                return
            }

            guessResult.value = isGuessCorrect(randomInt.value, guess)
            if (guess == randomInt.value) {
                gameOver.value = true
            } else {
                guessesLeft.value -= 1
                if (guessesLeft.value <= 0) {
                    guessResult.value = "No guesses left. Game over!"
                    gameOver.value = true
                }
            }
        } else if (gameMode.value == "city") {
            if (usersGuess.value.lowercase() == correctCity.value.name.lowercase()) {
                guessResult.value = "Correct!"
                gameOver.value = true
            } else {
                guessesLeft.value -= 1
                guessResult.value = "Incorrect. Try again..."
                if (guessesLeft.value <= 0) {
                    guessResult.value = "No guesses left. Game over!"
                    gameOver.value = true
                }
            }
        }
    }


        // Reset game and state variables
        fun resetGame() {

            correctCity.value = cities.random() // Set to a random city from the list in the data class

            guessResult.value = "Make a guess..."
            gameOver.value = false
            guessesLeft.value = 5
            randomInt.value = generateRandomNumber()
            usersGuess.value = ""
            gameMode.value = "number"
            hintMessage.value = ""
        }
    }

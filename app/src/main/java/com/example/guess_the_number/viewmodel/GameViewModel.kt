package com.example.guess_the_number.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    // Create all the state we're passing around
    var usersGuess = mutableStateOf("")
    var guessesLeft = mutableStateOf(5)

    var difficultyLevel = mutableStateOf(  0) // state for difficulty, as of yet unimplemented

    var gameMode = mutableStateOf("number") // "number" or "city"

    // Hard coded city list game variables
    private val cities = listOf("Paris", "Tokyo", "London", "New York", "Sydney")
    var correctCity = mutableStateOf(cities.random())

    // Hint system for cities. Get first letter with 3 guesses left, length with 2, final letter with 1.

    var hintMessage = mutableStateOf("")

    fun revealHint() {
        hintMessage.value = when (guessesLeft.value) {
            3 -> "The first letter is '${correctCity.value.first()}'."
            2 -> "The city has ${correctCity.value.length} letters."
            1 -> "The last letter is '${correctCity.value.last()}'."
            else -> ""
        }
    }

    // Moving state from GameScreen

    var guessResult = mutableStateOf("Make a guess...")
    var gameOver = mutableStateOf(false)

    // Determine max value for random number generator
    val max: Int
        get() = when (difficultyLevel.value) {
            0 -> 31
            1 -> 51
            2 -> 101
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

            // Handle invalid guesses
            if (guess == null || guess !in 1 until max) {
                guessResult.value = "Please only guess a number between 1 and ${max - 1}"
                return
            }

            // Check if the number guess is correct
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
            // City mode
            if (usersGuess.value.equals(correctCity.value, ignoreCase = true)) {
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
            guessResult.value = "Make a guess..."
            gameOver.value = false
            guessesLeft.value = 5
            randomInt.value = generateRandomNumber()
            usersGuess.value = ""
            gameMode.value = "number"
            hintMessage.value = ""
            correctCity.value = ""
        }
    }

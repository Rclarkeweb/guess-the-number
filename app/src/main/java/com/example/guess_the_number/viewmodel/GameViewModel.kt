package com.example.guess_the_number.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {

    // Potential for if we want to add in other game modes
    var gameMode = mutableStateOf("number") // number for our standard game

    // Create all the state we're passing around
    var usersGuess = mutableStateOf("")
    var guessesLeft = mutableStateOf(5)

    var difficultyLevel = mutableStateOf(  0) // state for difficulty, as of yet unimplemented

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
    fun generateRandomNumber() = Random.nextInt(1, max) // consider making private? I don't fully know what that does...

    // Guess submission function
    fun submitGuess() {
        val guess = usersGuess.value.toIntOrNull()
        if (guess != null && guess == randomInt.value) {
            // correct guess logic in other file 🙈
        } else {
            guessesLeft.value -= 1
            println(randomInt.value)
            println(max)
            println(difficultyLevel.value)
        }
    }

    // Reset game and state variables
    fun resetGame() {
        guessesLeft.value = 5
        randomInt.value = generateRandomNumber()
        usersGuess.value = ""
    }
}
package com.example.guess_the_number.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.guess_the_number.ui.theme.Purple40

@Composable
fun DifficultyLevelComponent(
    selectedLevel: Int, // from ViewModel state
    onLevelSelected: (Int) -> Unit // function to update the ViewModel state
) {
    // List of difficulty levels
    val levels = listOf("Easy", "Medium", "Hard")

    // Using index for level number to match the ViewModel difficultyLevel.value
    Row() {
        levels.forEachIndexed() { index, level ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (index == selectedLevel), // if index (o, 1, 2) matches the current state 'selectedValue' value
                    onClick = { onLevelSelected(index) }, // set the index (0, 1, 2) to be the current state value
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Purple40, // selected colour
                        unselectedColor = Color.Gray // unselected colour
                    )
                )
                Text(text = level) // RadioButton label (one of the levels list options)
            }
        }
    }
}
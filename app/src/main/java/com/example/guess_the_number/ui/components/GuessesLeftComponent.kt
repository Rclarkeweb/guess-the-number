package com.example.guess_the_number.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun GuessesLeftComponent(
    guessesLeft: Int, // number of guesses left
    modifier: Modifier = Modifier,
    ) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    )
    {
        Text(
            text = "Guesses left: $guessesLeft",
            textAlign = TextAlign.Right,
            style = TextStyle(
                fontSize = 15.sp,
                color = Purple40,
                fontWeight = FontWeight.Light
            ),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.padding(bottom = 30.dp), // Optional padding for the Row
            horizontalArrangement = Arrangement.Start // Align boxes to the start
        ) {
            // Display 5 circle to represent each guess left
            for (number in 1..guessesLeft) {
                Box(
                    modifier = Modifier
                        .size(15.dp) // circle size
                        .background(Purple40, shape = CircleShape)
                        .border(2.dp, Purple40, shape = CircleShape) // border on each circle
                )

                // Only apply space between the first 4 circles
                if (number < 5) {
                Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}
package com.example.guess_the_number.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guess_the_number.ui.theme.Pink80
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80

@Composable
fun GuessResultComponent(
    text: String,
    circleSize: Int,
    modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .size(circleSize.dp) // circle size
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Purple40, Purple80), // change colours (1: center, 2: outer)
                    radius = circleSize.toFloat() // How far the gradient reaches
                ),
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }

}
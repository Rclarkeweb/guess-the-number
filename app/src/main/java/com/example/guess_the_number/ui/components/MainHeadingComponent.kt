package com.example.guess_the_number.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guess_the_number.ui.theme.Purple40

@Composable
fun MainHeadingComponent(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 40.sp,
            color = Purple40,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .padding(bottom = 30.dp)
    )
}
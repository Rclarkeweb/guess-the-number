package com.example.guess_the_number.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.guess_the_number.R

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    value: String, // value will be a string
    onValueChange: (String) -> Unit // Function to call when input changes
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(R.string.input_label)) },
        singleLine = true, // Only allow one line of text in the input field
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}
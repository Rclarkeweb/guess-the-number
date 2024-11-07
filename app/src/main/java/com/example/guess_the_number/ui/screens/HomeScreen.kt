package com.example.guess_the_number.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guess_the_number.ui.components.ButtonComponent
import com.example.guess_the_number.ui.components.MainHeadingComponent
import com.example.guess_the_number.ui.theme.Purple40
import com.example.guess_the_number.ui.theme.Purple80

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    ) {
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
        MainHeadingComponent("Can you guess the number?")

        Row(
            modifier = Modifier.padding(top = 40.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
                ButtonComponent(
                    onClick = {  navController.navigate("howtoplay") },
                    label = "How to play",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )

                ButtonComponent(
                    onClick = { navController.navigate("game") },
                    label = "Start Game",
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )
            }
    }
}
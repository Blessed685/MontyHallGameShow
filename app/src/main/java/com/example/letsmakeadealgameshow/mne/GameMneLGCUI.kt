package com.example.letsmakeadealgameshow.mne

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.first

@Composable
fun MontyHallGameShow(){
    var nickname by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var stage by remember { mutableStateOf(0) }
    var playerPick by remember { mutableStateOf<Int?>(null) }
    var revealedGoat by remember { mutableStateOf<Int?>(null) }
    var carDoor by remember { mutableStateOf((1..3).random()) }
    var goatDoors by remember { mutableStateOf((1..3).filter { it != carDoor }.shuffled()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "MONTY HALL GAME SHOW",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(75.dp))

        if (stage == 0) {
//            message = "Welcome to Monty Hall Game!"
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Enter your nickname") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))
            if (message.isEmpty()){
                message = "Welcome to Monty Hall Game!"
            }
            Button(onClick = {
                if (nickname.isBlank()) {
                    message = "Nickname cannot be empty!"
                } else {
                    stage = 1
                    message = ""
//                    message = "Hello, $nickname! Pick a door."
                }
            }) {
                Text("Start Game")
            }
        }
        Spacer(modifier = Modifier.height(75.dp))
        Text(text = message,
            fontSize = 20.sp,
            textAlign = TextAlign.Center)

        if (stage == 1) {
            Text(text = "Hello, $nickname! Pick a door.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = {
                    playerPick = 1
                    revealedGoat = goatDoors.first { it != playerPick }
                    message = "Door $revealedGoat has a goat!\nDo you want to stick with Door $playerPick or switch?"
                    stage = 2
                }) {
                    Text("Door 1")
                }
                Button(onClick = {
                    playerPick = 2
                    revealedGoat = goatDoors.first { it != playerPick }
                    message = "Door $revealedGoat has a goat!\nDo you want to stick with Door $playerPick or switch?"
                    stage = 2
                }) {
                    Text("Door 2")
                }
                Button(onClick = {
                    playerPick = 3
                    revealedGoat = goatDoors.first { it != playerPick }
                    message = "Door $revealedGoat has a goat!\nDo you want to stick with Door $playerPick or switch?"
                    stage = 2
                }) {
                    Text("Door 3")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MontyHallGameShowPreview(){
    MontyHallGameShow()
}
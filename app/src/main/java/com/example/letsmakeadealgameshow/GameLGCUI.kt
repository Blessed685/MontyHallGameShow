package com.example.letsmakeadealgameshow

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LetsMakeADeal() {
    var nickname by remember { mutableStateOf("") }
    var playerPick by remember { mutableStateOf<Int?>(null) }
    var carDoor by remember { mutableStateOf((1..3).random()) }
    var goatDoors by remember { mutableStateOf((1..3).filter { it != carDoor }.shuffled()) }
    var revealedGoat by remember { mutableStateOf<Int?>(null) }
    var finalPick by remember { mutableStateOf<Int?>(null) }
    var message by remember { mutableStateOf("Welcome to Monty Hall Game!") }
    var stage by remember { mutableStateOf(0) } // 0=enter nickname,1=pick door,2=stick/switch,3=game over

    fun resetGame() {
        playerPick = null
        finalPick = null
        revealedGoat = null
        carDoor = (1..3).random()
        goatDoors = (1..3).filter { it != carDoor }.shuffled()
        message = "Pick a door!"
        stage = 1
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Monty Hall Game",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Stage 0: Enter nickname
        if (stage == 0) {
            OutlinedTextField(
                value = nickname,
                onValueChange = { nickname = it },
                label = { Text("Enter your nickname") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                if (nickname.isBlank()) {
                    message = "Nickname cannot be empty!"
                } else {
                    message = "Hello, $nickname! Pick a door."
                    stage = 1
                }
            }) {
                Text("Start Game")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        // Stage 1: Pick a door
        if (stage == 1) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                (1..3).forEach { door ->
                    Button(onClick = {
                        playerPick = door
                        revealedGoat = goatDoors.first { it != playerPick }
                        message = "Door $revealedGoat has a goat!\nDo you want to stick with Door $playerPick or switch?"
                        stage = 2
                    }) {
                        Text("Door $door")
                    }
                }
            }
        }

        // Stage 2: Stick or switch
        if (stage == 2) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = {
                    finalPick = playerPick
                    stage = 3
                    message = if (finalPick == carDoor) "You won the car! 🚗" else "Sorry, it's a goat 🐐"
                }) {
                    Text("Stick")
                }
                Button(onClick = {
                    finalPick = (1..3).first { it != playerPick && it != revealedGoat }
                    stage = 3
                    message = if (finalPick == carDoor) "You won the car! 🚗" else "Sorry, it's a goat 🐐"
                }) {
                    Text("Switch")
                }
            }
        }

        // Stage 3: Game over, show Play Again
        if (stage == 3) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { resetGame() }) {
                Text("Play Again")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGame (){
    LetsMakeADeal()
}
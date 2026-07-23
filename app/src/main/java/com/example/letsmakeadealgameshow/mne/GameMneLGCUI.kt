package com.example.letsmakeadealgameshow.mne

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun MontyHallGameShow(){
    var nickname by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var stage by remember { mutableStateOf(0) } // 0=enter nickname,1=pick door,2=stick/switch,3=game over

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

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MontyHallGameShowPreview(){
    MontyHallGameShow()
}
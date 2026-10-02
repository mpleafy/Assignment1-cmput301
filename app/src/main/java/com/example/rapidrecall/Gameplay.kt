package com.example.rapidrecall

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

fun numberGenerator(sequenceLength: Int): String {
    val target = StringBuilder()
    for (i in 0..<sequenceLength) {
        target.append(((0..9).random()).toString())
    }
    return target.toString()
}

/*
Represents the different stages of the game.

Used to control which part of the gameplay is currently displayed.
 */
enum class GameState {
    SETUP,
    START,
    SEQUENCE,
    GUESS,
    RESULT
}

@Composable
fun GamePlayScreen(
    onSetSequenceLength: (Int) -> Unit,
    onGetSequenceLength: () -> Int,
    onSetTarget: (String) -> Unit,
    onGetTarget: () -> String,
    onSetGuess: (String) -> Unit,
    onGetGuess: () -> String,
    onSetCorrect: () -> Unit,
    onGetCorrect: () -> Boolean,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier
){
    var gameState by remember {
        mutableStateOf(GameState.SETUP)
    }

    var newSequenceLength by remember { mutableStateOf("")}
    var newGuess by remember { mutableStateOf("")}

    when (gameState) {
        GameState.SETUP -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Button(
                    onClick = onHomeClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF69B4),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(0xFFCC5490)
                    )
                ) {
                    Text("HOME")
                }
                //Title
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "INPUT A NUMBER",
                        color = Color(0xFF069494),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "BETWEEN 1 AND 10",
                        color = Color(0xFF069494),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    //user input
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = newSequenceLength,
                            onValueChange = { newSequenceLength = it },
                            label = { Text("Enter Number") },
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = {
                            if (newSequenceLength.isNotBlank() && (newSequenceLength.trim().toIntOrNull()?: 0) in 1..10) {
                                onSetSequenceLength(newSequenceLength.toInt())
                                onSetTarget(numberGenerator(onGetSequenceLength()))
                                gameState = GameState.START
                            }
                            newSequenceLength = ""
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF69B4),
                                contentColor = Color.White
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0xFFCC5490)
                            )
                        ) {
                            Text(
                                text = "ENTER",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
        GameState.START -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "READY TO START?",
                        color = Color(0xFF069494),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {
                        gameState = GameState.SEQUENCE
                    },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF69B4),
                            contentColor = Color.White
                        ),
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color(0xFFCC5490)
                        )
                    ) {
                        Text(
                            text = "START",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
        GameState.SEQUENCE -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    sequenceAnimation(target = onGetTarget(), onFinished = {gameState = GameState.GUESS})
                }
            }
        }
        GameState.GUESS -> {

            Column(
                modifier = modifier.fillMaxSize()
            ) {
                //Title
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "ENTER YOUR GUESS:",
                        color = Color(0xFF069494),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    //user input
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = newGuess,
                            onValueChange = { newGuess = it },
                            label = { Text("Guess") },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = {
                            if (newGuess.isNotBlank()) {
                                onSetGuess(newGuess)
                                onSetCorrect()
                                gameState = GameState.RESULT

                            }
                            newGuess = ""
                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFF69B4),
                                contentColor = Color.White
                            ),
                            border = BorderStroke(
                                width = 2.dp,
                                color = Color(0xFFCC5490)
                            )
                        ) {
                            Text(
                                text = "ENTER",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }

        }
        GameState.RESULT -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                Button(
                    onClick = onHomeClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF69B4),
                        contentColor = Color.White
                    ),
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(0xFFCC5490)
                    )
                ) {
                    Text("HOME")
                }

                if (onGetCorrect()) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "CORRECT SEQUENCE!",
                            color = Color.Green,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "INCORRECT SEQUENCE",
                            color = Color.Red,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "TARGET: ${onGetTarget()}\nGUESS: ${onGetGuess()}",
                            color = Color(0xFF069494),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

        }

    }

}

@Composable
fun sequenceAnimation(target: String, onFinished: () -> Unit) {
    var message by remember {mutableStateOf("Ready?")}

    LaunchedEffect(target) {
        message = "Ready?"
        delay(1000)

        message = "GO!"
        delay(1000)

        for (i in target.indices) {
            message = target[i].toString()
            delay(1000)
        }
        onFinished()
    }
    Text(
        text = message,
        color = Color(0xFF069494),
        fontSize = 50.sp,
        fontWeight = FontWeight.ExtraBold
    )

}
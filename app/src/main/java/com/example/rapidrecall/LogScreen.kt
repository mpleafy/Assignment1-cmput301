package com.example.rapidrecall

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogScreen(
    onGetAttempts: () -> List<Attempt>,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
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
        //title
        Text(
            text = "LOG",
            color = Color(0xFF069494),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        //column headers
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogHeader(
                text = "Length",
                modifier = Modifier.weight(1f)
            )

            LogHeader(
                text = "Input",
                modifier = Modifier.weight(1.5f)
            )

            LogHeader(
                text = "Target",
                modifier = Modifier.weight(1.5f)
            )

            LogHeader(
                text = "Correct",
                modifier = Modifier.weight(1f)
            )

            LogHeader(
                text = "Timestamp",
                modifier = Modifier.weight(1.5f)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(onGetAttempts()) { _, attempt ->
                LogRow(
                    sequenceLength = attempt.sequenceLength.toString(),
                    userInput = attempt.guess,
                    targetSequence = attempt.target,
                    isCorrect = attempt.correct,
                    timestamp = attempt.guessTime
                )
            }
        }
    }
}

@Composable
private fun LogHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier.padding(horizontal = 4.dp),
        style = MaterialTheme.typography.labelLarge,
        color = Color(0xFF069494),
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun LogRow(
    sequenceLength: String,
    userInput: String,
    targetSequence: String,
    isCorrect: Boolean,
    timestamp: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = sequenceLength,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = userInput,
            modifier = Modifier
                .weight(1.5f)
                .padding(horizontal = 4.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = targetSequence,
            modifier = Modifier
                .weight(1.5f)
                .padding(horizontal = 4.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = if (isCorrect) "√" else "X",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 4.dp),
            textAlign = TextAlign.Center,
            color = if (isCorrect) {
                Color(0xFF2E7D32)
            } else {
                Color(0xFFC62828)
            }
        )

        Text(
            text = timestamp,
            modifier = Modifier
                .weight(1.5f)
                .padding(horizontal = 4.dp),
            textAlign = TextAlign.Center
        )
    }

    HorizontalDivider()
}
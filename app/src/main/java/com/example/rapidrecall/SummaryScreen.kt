package com.example.rapidrecall

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SummaryScreen(
    onGetAttempts: () -> List<Attempt>,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
        Spacer(modifier = Modifier.height(15.dp))
        //title
        Text(
            text = "SUMMARY",
            color = Color(0xFF069494),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "NUMBER OF ATTEMPTS",
            color = Color(0xFF069494),
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = onGetAttempts().count().toString(),
            color = Color(0xFF00F0FF),
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "CORRECT ATTEMPTS",
            color = Color(0xFF069494),
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = onGetAttempts().count{it.correct}.toString(),
            color = Color(0xFF00F0FF),
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "OVERALL ACCURACY",
            color = Color(0xFF069494),
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = if (onGetAttempts().isEmpty()) "0%" else String.format("%.1f%%", onGetAttempts().count{it.correct}/(onGetAttempts().count()).toDouble() * 100),
            color = Color(0xFF00F0FF),
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

}
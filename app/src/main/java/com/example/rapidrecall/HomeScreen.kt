package com.example.rapidrecall

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
fun HomeScreen(
    onPlayClick: () -> Unit,
    onLogClick: () -> Unit,
    onSummaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        //Title
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "RAPID",
                color = Color(0xFF069494),
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "RECALL",
                color = Color(0xFF069494),
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold
            )

        }
        //navigation buttons
        Column(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = onPlayClick,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
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
                    text = "PLAY",
                    fontSize = 30.sp
                )
            }

            Button(onClick = onLogClick,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
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
                    text = "LOG",
                    fontSize = 30.sp
                )
            }

            Button(onClick = onSummaryClick,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
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
                    text = "SUMMARY",
                    fontSize = 30.sp
                )
            }


        }
    }
}
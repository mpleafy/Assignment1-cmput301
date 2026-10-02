package com.example.rapidrecall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.rapidrecall.ui.theme.RapidRecallTheme
/*
Main entry point for the rapid recall app

Sets up the compose UI and creates the GameManager and AttemptRepository,
which are used to manage game data. Keeping these objects here allows the different
screens to access the same game data.
 */
class MainActivity : ComponentActivity() {
    private val attemptRepository = AttemptRepository()
    private val gameManager = GameManager(attemptRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RapidRecallTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(
                        gameManager = gameManager,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

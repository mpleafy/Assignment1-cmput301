package com.example.rapidrecall

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

enum class Screen {
    HOME,
    PLAY,
    LOG,
    SUMMARY
}

@Composable
fun Navigation(
    gameManager: GameManager,
    modifier: Modifier = Modifier
){
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    when (currentScreen) {
        Screen.HOME -> {
            HomeScreen(
                onPlayClick = {
                    currentScreen = Screen.PLAY
                },
                onLogClick = {
                    currentScreen = Screen.LOG
                },
                onSummaryClick = {
                    currentScreen = Screen.SUMMARY
                },
                modifier = modifier
            )
        }
        Screen.PLAY -> {
            GamePlayScreen(
                onSetSequenceLength = {gameManager.setSequenceLength(it)},
                onGetSequenceLength = {gameManager.getSequenceLength()},
                onSetTarget = {gameManager.setTarget(it)},
                onGetTarget = {gameManager.getTarget()},
                onSetGuess = {gameManager.setGuess(it)},
                onGetGuess = {gameManager.getGuess()},
                onSetCorrect = {gameManager.setCorrect()},
                onGetCorrect = {gameManager.getCorrect()},
                onHomeClick = {
                    currentScreen = Screen.HOME
                },
                modifier = Modifier
            )

        }
        Screen.LOG -> {
            LogScreen(
                onGetAttempts = {gameManager.getAttempts()},
                onHomeClick = {
                    currentScreen = Screen.HOME
                },
                modifier = Modifier
            )
        }

        Screen.SUMMARY -> {
            SummaryScreen(
                onGetAttempts = {gameManager.getAttempts()},
                onHomeClick = {
                    currentScreen = Screen.HOME
                },
                modifier = Modifier
            )

        }
    }
}
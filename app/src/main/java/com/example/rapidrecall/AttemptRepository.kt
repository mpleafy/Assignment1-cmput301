package com.example.rapidrecall

import androidx.compose.runtime.mutableStateListOf
/*
Stores all game attempts made during the current session.

uses a mutable list so new attempts can be added as the player completes games,
while exposing the attempts as a read-only list to other classes.
 */
class AttemptRepository {
    private val _attempts = mutableStateListOf<Attempt>()

    fun addAttempt(attempt: Attempt){
        _attempts.add(attempt)
    }

    val attempts: List<Attempt>
        get() = _attempts

}
package com.example.rapidrecall

import androidx.compose.runtime.mutableStateListOf

class AttemptRepository {
    private val _attempts = mutableStateListOf<Attempt>()

    fun addAttempt(attempt: Attempt){
        _attempts.add(attempt)
    }

    val attempts: List<Attempt>
        get() = _attempts

}
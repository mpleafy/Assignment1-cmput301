package com.example.rapidrecall

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class GameManager(
    private val attemptRepository: AttemptRepository
) {
    fun setSequenceLength(inputLength: Int){
        val attempt = Attempt(inputLength)
        attemptRepository.addAttempt(attempt)
    }

    fun getSequenceLength(): Int { //new attempt object
        return attemptRepository.attempts.last().sequenceLength
    }

    fun setTarget(target: String) {
        attemptRepository.attempts.last().target = target
    }

    fun getTarget(): String {
        return attemptRepository.attempts.last().target
    }

    fun setGuess(guess: String) {
        attemptRepository.attempts.last().guess = guess
        attemptRepository.attempts.last().guessTime = SimpleDateFormat(
            "HH:mm:ss",
            Locale.getDefault()
        ).format(Date())
    }

    fun getGuess(): String {
        return attemptRepository.attempts.last().guess
    }

    fun setCorrect(){
        attemptRepository.attempts.last().correct = (attemptRepository.attempts.last().guess == attemptRepository.attempts.last().target)
    }

    fun getCorrect(): Boolean {
        return attemptRepository.attempts.last().correct
    }

    fun getAttempts(): List<Attempt> {
        return attemptRepository.attempts
    }



}


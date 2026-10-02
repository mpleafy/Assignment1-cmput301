package com.example.rapidrecall
/*
Stores the information for one game attempt

The sequence length is set when the attempt is created,
while the target, guess, result, and timestamp are filled in during gameplay.
 */

data class Attempt (
    val sequenceLength: Int
) {
    var target: String = ""
    var guess: String = ""
    var correct: Boolean = false
    var guessTime: String = "" //timestamp is when guess is made
}
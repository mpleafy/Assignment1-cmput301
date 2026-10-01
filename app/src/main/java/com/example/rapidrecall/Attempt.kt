package com.example.rapidrecall


data class Attempt (
    val sequenceLength: Int
) {
    var target: String = ""
    var guess: String = ""
    var correct: Boolean = false
    var guessTime: String = "" //timestamp is when guess is made
}
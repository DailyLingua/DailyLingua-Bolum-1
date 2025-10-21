package com.example.dailylingua.data.model

data class Word(
    val id: Int,
    val word: String,
    val translation: String,
    val example: String,
    val options: List<String>,
    val correct: String
)

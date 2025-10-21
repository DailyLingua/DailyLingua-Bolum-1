package com.example.dailylingua.data.repository

import android.content.Context
import com.example.dailylingua.data.model.Word
import com.example.dailylingua.utils.JsonUtils
import java.time.LocalDate

class WordRepository(private val context: Context, private val language: String) {

    private val words: List<Word> by lazy {
        JsonUtils.loadWordsFromAssets(context, when(language){
            "de" -> "words_de.json"
            "ru" -> "words_ru.json"
            else -> "words_en.json"
        })
    }

    fun allWords(): List<Word> = words

    fun todaysWord(): Word? {
        if (words.isEmpty()) return null
        val dayOfYear = try {
            LocalDate.now().dayOfYear
        } catch (e: Exception) {
            // fallback to millis
            (System.currentTimeMillis() / (24*60*60*1000)).toInt()
        }
        val index = dayOfYear % words.size
        return words[index]
    }

    fun randomQuizWords(count: Int): List<Word> {
        if (words.isEmpty()) return emptyList()
        return words.shuffled().take(count)
    }
}

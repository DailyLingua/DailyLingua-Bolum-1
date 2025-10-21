package com.example.dailylingua.ui.daily

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylingua.R
import com.example.dailylingua.data.repository.WordRepository

class DailyWordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_word)

        val prefs = getSharedPreferences("daily_prefs", MODE_PRIVATE)
        val lang = prefs.getString("selectedLanguage", "en") ?: "en"

        val repo = WordRepository(this, lang)
        val tvWord = findViewById<TextView>(R.id.tv_word)
        val tvTranslation = findViewById<TextView>(R.id.tv_translation)
        val tvExample = findViewById<TextView>(R.id.tv_example)
        val btnQuiz = findViewById<Button>(R.id.btn_quiz)
        val btnProgress = findViewById<Button>(R.id.btn_progress)

        val word = repo.todaysWord()
        if (word != null) {
            tvWord.text = word.word
            tvTranslation.text = word.translation
            tvExample.text = word.example
        } else {
            tvWord.text = getString(R.string.no_words)
        }

        btnQuiz.setOnClickListener {
            startActivity(Intent(this, com.example.dailylingua.ui.quiz.QuizActivity::class.java))
        }

        btnProgress.setOnClickListener {
            startActivity(Intent(this, com.example.dailylingua.ui.progress.ProgressActivity::class.java))
        }
    }
}

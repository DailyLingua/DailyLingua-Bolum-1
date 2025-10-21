package com.example.dailylingua.ui.quiz

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylingua.R
import com.example.dailylingua.data.repository.WordRepository

class QuizActivity : AppCompatActivity() {
    private var currentIndex = 0
    private var score = 0
    private lateinit var quizWords: List<com.example.dailylingua.data.model.Word>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val prefs = getSharedPreferences("daily_prefs", MODE_PRIVATE)
        val lang = prefs.getString("selectedLanguage", "en") ?: "en"

        val repo = WordRepository(this, lang)
        quizWords = repo.randomQuizWords(5)

        showQuestion()
    }

    private fun showQuestion() {
        if (currentIndex >= quizWords.size) {
            showResult()
            return
        }

        val q = quizWords[currentIndex]
        val tvQuestion = findViewById<TextView>(R.id.tv_question)
        val btnA = findViewById<Button>(R.id.btn_a)
        val btnB = findViewById<Button>(R.id.btn_b)
        val btnC = findViewById<Button>(R.id.btn_c)
        val btnD = findViewById<Button>(R.id.btn_d)
        val btnNext = findViewById<Button>(R.id.btn_next)

        tvQuestion.text = q.example

        val options = q.options.shuffled()
        btnA.text = options.getOrNull(0) ?: ""
        btnB.text = options.getOrNull(1) ?: ""
        btnC.text = options.getOrNull(2) ?: ""
        btnD.text = options.getOrNull(3) ?: ""

        val optionButtons = listOf(btnA, btnB, btnC, btnD)
        optionButtons.forEach { btn ->
            btn.setBackgroundColor(Color.LTGRAY)
            btn.isEnabled = true
            btn.setOnClickListener {
                optionButtons.forEach { it.isEnabled = false }
                if (btn.text.toString() == q.correct) {
                    btn.setBackgroundColor(Color.parseColor("#4CAF50"))
                    score++
                } else {
                    btn.setBackgroundColor(Color.RED)
                    // highlight correct
                    optionButtons.find { it.text.toString() == q.correct }?.setBackgroundColor(Color.parseColor("#4CAF50"))
                }
            }
        }

        btnNext.setOnClickListener {
            currentIndex++
            showQuestion()
        }
    }

    private fun showResult() {
        val prefs = getSharedPreferences("daily_prefs", MODE_PRIVATE)
        val total = prefs.getInt("total_quizzes", 0) + 1
        val correct = prefs.getInt("correct_answers", 0) + score
        prefs.edit().putInt("total_quizzes", total).putInt("correct_answers", correct).apply()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.quiz_finished))
            .setMessage(getString(R.string.your_score, score, quizWords.size))
            .setPositiveButton(android.R.string.ok) { _, _ -> finish() }
            .show()
    }
}

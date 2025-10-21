package com.example.dailylingua.ui.progress

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylingua.R

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val prefs = getSharedPreferences("daily_prefs", MODE_PRIVATE)
        val totalQuizzes = prefs.getInt("total_quizzes", 0)
        val correctAnswers = prefs.getInt("correct_answers", 0)

        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val tvPercent = findViewById<TextView>(R.id.tv_percent)

        val percent = if (totalQuizzes == 0) 0 else (correctAnswers * 100 / (totalQuizzes * 5))
        progressBar.progress = percent
        tvPercent.text = getString(R.string.progress_percent, percent)
    }
}

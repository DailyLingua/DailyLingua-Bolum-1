package com.example.dailylingua.ui.language

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.dailylingua.R

class LanguageSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_select)

        val btnEn = findViewById<Button>(R.id.btn_en)
        val btnDe = findViewById<Button>(R.id.btn_de)
        val btnRu = findViewById<Button>(R.id.btn_ru)

        val prefs = getSharedPreferences("daily_prefs", MODE_PRIVATE)

        btnEn.setOnClickListener {
            prefs.edit().putString("selectedLanguage", "en").apply()
            startActivity(Intent(this, com.example.dailylingua.ui.daily.DailyWordActivity::class.java))
            finish()
        }

        btnDe.setOnClickListener {
            prefs.edit().putString("selectedLanguage", "de").apply()
            startActivity(Intent(this, com.example.dailylingua.ui.daily.DailyWordActivity::class.java))
            finish()
        }

        btnRu.setOnClickListener {
            prefs.edit().putString("selectedLanguage", "ru").apply()
            startActivity(Intent(this, com.example.dailylingua.ui.daily.DailyWordActivity::class.java))
            finish()
        }
    }
}

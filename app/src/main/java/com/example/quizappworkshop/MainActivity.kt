package com.example.quizappworkshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnStartQuiz: Button = findViewById(R.id.btnStartQuiz)
        val etUsername: EditText = findViewById(R.id.etUsername)

        btnStartQuiz.setOnClickListener {
            val username = etUsername.text.toString()
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }
    }
}
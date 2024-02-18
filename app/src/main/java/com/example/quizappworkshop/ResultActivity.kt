package com.example.quizappworkshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvResult: TextView = findViewById(R.id.tvResult)

        // Calculate score
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)
        val username= intent?.getStringExtra("username")

        // Display result
        tvResult.text = "$username score is $score out of $total"

    }

}
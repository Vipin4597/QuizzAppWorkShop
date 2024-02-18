package com.example.quizappworkshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class QuizActivity : AppCompatActivity() {
    private val questions = arrayOf(
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'Romeo and Juliet'?"
    )

    private val options = arrayOf(
        arrayOf("Paris", "London", "Berlin", "Rome"),
        arrayOf("Mars", "Jupiter", "Venus", "Saturn"),
        arrayOf("William Shakespeare", "Jane Austen", "Charles Dickens", "Mark Twain")
    )

    private val correctAnswers = arrayOf(1, 0, 0) // Predefined correct answers
    private val selectedAnswers = IntArray(questions.size){-1}
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val tvQuestion: TextView = findViewById(R.id.tvQuestion)
        val option1: RadioButton = findViewById(R.id.option1)
        val option2: RadioButton = findViewById(R.id.option2)
        val option3: RadioButton = findViewById(R.id.option3)
        val option4: RadioButton = findViewById(R.id.option4)
        val btnNext: Button = findViewById(R.id.btnNext)

        displayQuestion(tvQuestion, option1, option2, option3, option4)

        option1.setOnClickListener { selectedAnswers[currentQuestionIndex] = 0 }
        option2.setOnClickListener { selectedAnswers[currentQuestionIndex] = 1 }
        option3.setOnClickListener { selectedAnswers[currentQuestionIndex] = 2 }
        option4.setOnClickListener { selectedAnswers[currentQuestionIndex] = 3 }

        btnNext.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion(tvQuestion, option1, option2, option3, option4)
            } else {
                val username = intent.getStringExtra("username")
                val intent = Intent(this, ResultActivity::class.java)
                val score = calculateScore(selectedAnswers)
                intent.putExtra("score", score)
                intent.putExtra("total", selectedAnswers.size)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }


    }

    private fun displayQuestion(
        tvQuestion: TextView,
        option1: RadioButton,
        option2: RadioButton,
        option3: RadioButton,
        option4: RadioButton
    ) {
        tvQuestion.text = questions[currentQuestionIndex]

        option1.text = options[currentQuestionIndex][0]
        option2.text = options[currentQuestionIndex][1]
        option3.text = options[currentQuestionIndex][2]
        option4.text = options[currentQuestionIndex][3]

        // Check if user has previously selected an answer for the current question
        val selectedAnswerIndex = selectedAnswers[currentQuestionIndex]
        if (selectedAnswerIndex != -1) {
            when (selectedAnswerIndex) {
                0 -> option1.isChecked = true
                1 -> option2.isChecked = true
                2 -> option3.isChecked = true
                3 -> option4.isChecked = true
            }
        } else {
            // Clear radio button selection if user has not selected any answer previously
            option1.isChecked = false
            option2.isChecked = false
            option3.isChecked = false
            option4.isChecked = false
        }
    }

    private fun calculateScore(selectedAnswers: IntArray): Int {
        var score = 0
        val correctAnswers = arrayOf(1, 0, 0) // Predefined correct answers

        if (selectedAnswers != null) {
            for (i in selectedAnswers.indices) {
                if (selectedAnswers[i] == correctAnswers[i]) {
                    score++
                }
            }
        }
        return score
    }
}

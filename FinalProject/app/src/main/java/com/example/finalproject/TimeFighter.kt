package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class TimeFighter : AppCompatActivity() {

    private var score = 0
    private var gameStarted = false
    private lateinit var countdownTimer: CountDownTimer
    internal val initialCountDown: Long = 10000
    internal val countdownInterval: Long = 1000
    internal var timeLeftOnTimer: Long = 10000

    //private val addButton: Button by lazy { findViewById<Button>(R.id.add_button) }

    private val tapMeButton: Button by lazy { findViewById<Button>(R.id.tapMeButton)}
    private val gameScoreTextView: TextView by lazy {findViewById<TextView>(R.id.GameScoreTextView)}
    val timeLeftTextView: TextView by lazy {findViewById<TextView>(R.id.TimeLeftTextView)}

    private val viewModel: FinalViewModel by lazy{
        ViewModelProviders.of(this).get(FinalViewModel::class.java)
    }

    companion object {
        private const val SCORE_KEY = "SCORE_KEY"
        private const val TIME_LEFT_KEY = "TIME_LEFT_KEY"

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, TimeFighter::class.java).apply {

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timefighter)

        //tapMeButton = findViewById(R.id.tapMeButton)
//        gameScoreTextView = findViewById(R.id.GameScoreTextView)
//        timeLeftTextView = findViewById(R.id.TimeLeftTextView)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOnTimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        } else
            resetGame()

        tapMeButton.setOnClickListener { view ->
            incrementScore()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY, score)
        outState.putLong(TIME_LEFT_KEY, timeLeftOnTimer)
        countdownTimer.cancel()
    }

    private fun resetGame() {

        score = 0
        gameScoreTextView.text = getString(R.string.your_score, score)

        val initialTimeLeft = initialCountDown / 1000
        timeLeftTextView.text = getString(R.string.time_left, initialTimeLeft)

        countdownTimer = object : CountDownTimer(initialCountDown, countdownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }
        gameStarted = false
    }

    private fun restoreGame() {
        gameScoreTextView.text = getString(R.string.your_score, score)

        val restoredTime = timeLeftOnTimer / 1000
        timeLeftTextView.text = getString(R.string.time_left, restoredTime)

        countdownTimer = object : CountDownTimer(timeLeftOnTimer, countdownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftOnTimer = millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time_left, timeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }

        countdownTimer.start()
        gameStarted = true
    }

    private fun startGame() {

        countdownTimer.start()
        gameStarted = true
    }

    private fun incrementScore() {
        if (!gameStarted)
            startGame()
        score++
        val newScore = getString(R.string.your_score, score)
        gameScoreTextView.text = newScore
    }

    private fun endGame() {
        //Toast.makeText(this, getString(R.string.gameOverMessage, score), Toast.LENGTH_LONG).show()
        //resetGame()
        viewModel.updateScore(score)
        val intent = TimeFighterEnd.newIntent(this@TimeFighter)
        startActivityForResult(intent, GAME1_CODE)

    }
}
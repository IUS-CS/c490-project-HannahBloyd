package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class HighScoresMainMenu : AppCompatActivity() {

    private val TFButton: Button by lazy { findViewById<Button>(R.id.game1_button) }
    private val CF2Button: Button by lazy { findViewById<Button>(R.id.game2_button) }
    private val viewModel: FinalViewModel by lazy {
        ViewModelProviders.of(this).get(FinalViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mm_hs)

        TFButton.setOnClickListener{
            viewModel.updateGame("TimeFighter")
            val intent = TFHighScores.newIntent(this@HighScoresMainMenu)
            startActivityForResult(intent, GAME1_CODE)
        }

        CF2Button.setOnClickListener{
            viewModel.updateGame("ConnectFour")
            val intent = TFHighScores.newIntent(this@HighScoresMainMenu)
            startActivityForResult(intent, GAME2_CODE)
        }
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, HighScoresMainMenu::class.java).apply {
            }
        }
    }
}
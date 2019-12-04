package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HowToTimeFighter : AppCompatActivity() {

    private val continueButton: Button by lazy { findViewById<Button>(R.id.continueTF_button) }
    private val backButton: Button by lazy { findViewById<Button>(R.id.returnTF_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tf_how_to)

        continueButton.setOnClickListener{
            val intent = TimeFighter.newIntent(this@HowToTimeFighter)
            startActivityForResult(intent, GAME1_CODE)
        }

        backButton.setOnClickListener{
            //finish()
            val intent = MainActivity.newIntent(this@HowToTimeFighter)
            startActivityForResult(intent, GAME1_CODE)
        }

    }

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, HowToTimeFighter::class.java).apply {

            }
        }
    }
}
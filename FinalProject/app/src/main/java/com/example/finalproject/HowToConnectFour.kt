package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HowToConnectFour : AppCompatActivity() {

    private val continueButton: Button by lazy { findViewById<Button>(R.id.continueCF_button) }
    private val backButton: Button by lazy { findViewById<Button>(R.id.returnCF_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cf_how_to)

        continueButton.setOnClickListener{
            val intent = CFCompat.newIntent(this@HowToConnectFour)       //Change to Connectfour.newIntent
            startActivityForResult(intent, GAME2_CODE)


        }

        backButton.setOnClickListener{
            val intent = MainActivity.newIntent(this@HowToConnectFour)
            startActivityForResult(intent, GAME2_CODE)
        }

    }

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, HowToConnectFour::class.java).apply {

            }
        }
    }
}
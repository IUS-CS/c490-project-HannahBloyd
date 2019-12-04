package com.example.finalproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProviders

const val GAME1_CODE = 0
const val GAME2_CODE = 1
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val game1Button: Button by lazy { findViewById<Button>(R.id.game1_button) }
    private val game2Button: Button by lazy { findViewById<Button>(R.id.game2_button) }

    private val viewModel: FinalViewModel by lazy {
        ViewModelProviders.of(this).get(FinalViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val intent = AddActivity.newIntent(this@MainActivity)
//        startActivityForResult(intent, ADD_CODE)

        game1Button.setOnClickListener{
            val intent = HowToTimeFighter.newIntent(this@MainActivity)
            startActivityForResult(intent, GAME1_CODE)

        }

        game2Button.setOnClickListener{
            val intent = HowToConnectFour.newIntent(this@MainActivity)
            startActivityForResult(intent, GAME2_CODE)
        }


    }

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivity::class.java).apply {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            Log.d(TAG, "Result code: $resultCode")
            return
        }
    }
}

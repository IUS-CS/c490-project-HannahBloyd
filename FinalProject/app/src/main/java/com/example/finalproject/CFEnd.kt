package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.cf_endscreen.*

class CFEnd : AppCompatActivity() {
    private val finaltextView: TextView by lazy {
        CFScoreView.findViewById<TextView>(R.id.CFScoreView)
    }
    private val submit: Button by lazy {
        submitButton.findViewById<Button>(R.id.submitButton)
    }
    private val viewModel: FinalViewModel by lazy {
        ViewModelProviders.of(this).get(FinalViewModel::class.java)
    }
    private val nameText: EditText by lazy {
        CFnameTextView.findViewById<EditText>(R.id.CFnameTextView)
    }
    private val backButton: Button by lazy { findViewById<Button>(R.id.returnEndCFButton) }
    private val highScoresButton: Button by lazy { findViewById<Button>(R.id.databaseCFButton) }

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, CFEnd::class.java).apply {

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cf_endscreen)

        val finalWinner = getString(R.string.cfWinner, viewModel.getwinner())
        finaltextView.text= finalWinner

        backButton.setOnClickListener{

            val intent = MainActivity.newIntent(this@CFEnd)
            startActivityForResult(intent, GAME1_CODE)
        }

        submit.setOnClickListener{
            val name :String = nameText.text.toString()
            val user = ScoreEntry(name, score)
            viewModel.writeNewUser(user, "CF")
        }

        highScoresButton.setOnClickListener{
            //viewModel.updateGame("ConnectFour")
            val intent = TFHighScores.newIntent(this@CFEnd)
            startActivityForResult(intent, GAME1_CODE)
        }

        //viewModel.reset()

    }
}
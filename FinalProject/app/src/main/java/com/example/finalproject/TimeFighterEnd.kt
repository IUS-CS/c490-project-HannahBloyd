package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.tf_endscreen.*
import org.w3c.dom.Text
import java.util.*

class TimeFighterEnd : AppCompatActivity() {

    private val finaltextView: TextView by lazy{
        TFScoreView.findViewById<TextView>(R.id.TFScoreView) }
    private val submit: Button by lazy{
        submitButton.findViewById<Button>(R.id.submitButton) }
    private val viewModel: FinalViewModel by lazy{
        ViewModelProviders.of(this).get(FinalViewModel::class.java) }
    private val nameText: EditText by lazy{
        nameTextView.findViewById<EditText>(R.id.nameTextView) }
    private val backButton: Button by lazy { findViewById<Button>(R.id.returnEndTFButton) }
    private val highScoresButton: Button by lazy { findViewById<Button>(R.id.databaseTFButton) }

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, TimeFighterEnd::class.java).apply {

            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tf_endscreen)


        val finalScore = getString(R.string.gameOverMessage, viewModel.getScore())
        finaltextView.text= finalScore

        backButton.setOnClickListener{

            val intent = MainActivity.newIntent(this@TimeFighterEnd)
            startActivityForResult(intent, GAME1_CODE)
        }

        submit.setOnClickListener{
            val name :String = nameText.text.toString()
            //val id = UUID.randomUUID()
            val user = ScoreEntry(name, score)
            viewModel.writeNewUser(user, "TF")
        }

        highScoresButton.setOnClickListener{
            viewModel.updateGame("TimeFighter")
            val intent = TFHighScores.newIntent(this@TimeFighterEnd)
            startActivityForResult(intent, GAME1_CODE)
        }
    }
}
package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError


class TFHighScores : AppCompatActivity() {

    private val nametextView: TextView by lazy{ findViewById<TextView>(R.id.scores_name_textview) }
    private val scorestextView: TextView by lazy{ findViewById<TextView>(R.id.scores_score_textview) }
    private val viewModel: FinalViewModel by lazy{
        ViewModelProviders.of(this).get(FinalViewModel::class.java) }
    private val returnb: Button by lazy{ findViewById<Button>(R.id.button)}
    private var nameList :String = ""
    private var scoreList :String = ""

    companion object {

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, TFHighScores::class.java).apply {

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.highscores_display)


        val ref = viewModel.referenceDatabase()
        ref.orderByChild("score").limitToLast(5).addChildEventListener(object: ChildEventListener{
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnap: DataSnapshot, p1: String?) {
                val something : ScoreEntry? = dataSnap.getValue(ScoreEntry::class.java)
                nameList = "${something!!.name}${'\n'}$nameList"
                scoreList = "${something!!.score}${'\n'}$scoreList "

                Log.d(TAG, "name = ${something!!.name} and score = ${something.score}")
                Log.d(TAG, "nameList = $nameList")

                nametextView.text = nameList
                scorestextView.text = scoreList
            }
        })

        returnb.setOnClickListener{
            val intent = MainActivity.newIntent(this@TFHighScores)
            startActivityForResult(intent, GAME1_CODE)
        }

    }
}
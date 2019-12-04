package com.example.finalproject

import android.graphics.PointF
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

private const val TAG2 = "FinalViewModel"
var score = 0
var currentBox: Points? = null
val database: FirebaseDatabase = FirebaseDatabase.getInstance()
val dbRef: DatabaseReference = database.reference
private var currentGame: String = "TimeFighter"
var circles = ArrayList<Points>(0)
var winner = 0

class FinalViewModel : ViewModel(){
    var grid: Array<Array<Int>> = emptyGrid()
    var currentPlayer: Int = 1

    fun getScore() : Int{
        return score
    }

    fun updateScore(value :Int) {
        score = value
    }

    fun updateGame(value: String){
        currentGame = value
    }

    fun writeNewUser(user :ScoreEntry, game :String) {

        if (game == "TF")
            dbRef.child("TimeFighter").push().setValue(user)
        else if (game == "CF")
            dbRef.child("ConnectFour").push().setValue(user)
    }

    fun referenceDatabase(): DatabaseReference{
        return database.getReference(currentGame)
    }

    fun emptyGrid(): Array<Array<Int>> {
        return Array(7) {
            Array(6) {
                0
            }
        }
    }

    fun play(x: Int, width: Float){
        if (winner == 0) {
            var height = 1325F
            for (y in 0..5) {
                if (grid[x][y] == 0) {
                    var pos: PointF = PointF()
                    pos.x = width
                    pos.y = height
                    currentBox = Points(currentPlayer, pos)
                    grid[x][y] = currentPlayer
                    circles.add(currentBox!!)
                    Log.d(TAG, "Playing at $x ,$y for player $currentPlayer")
                    checkWinner()
                    Log.d(TAG, "Winner = $winner and score = $score")
                    switchPlayer()
                    break
                }
                height -= 260F

            }
        }
    }

    fun switchPlayer(){

        if (currentPlayer == 1){
            currentPlayer = 2
        }
        else {
            currentPlayer = 1
        }

    }

    fun checkWinner (){
        for (x in 0..6){
            for (y in 0..5){
                if (grid[x][y] != 0){
                    if (checkRows(x,y) || checkCols(x, y) || checkDiag(x, y))
                        winner = grid[x][y]
                }
            }
        }
    }

    fun checkRows(x:Int, y:Int) :Boolean {
        if (x < 4)
            if (grid[x][y] == grid[x+1][y] && grid[x][y] == grid[x+2][y] && grid[x][y] == grid[x+3][y])
                return true
        return false
    }

    fun checkCols(x:Int, y:Int) :Boolean{
        if (y < 3)
            if (grid[x][y] == grid[x][y+1] && grid[x][y] == grid[x][y+2] && grid[x][y] == grid[x][y+3])
                return true
        return false
    }

    fun checkDiag(x:Int, y:Int) :Boolean{
        if (x < 4 && y < 3)
            if (grid[x][y] == grid[x + 1][y + 1] && grid[x][y] == grid[x + 2][y + 2] && grid[x][y] == grid[x + 3][y + 3])
                return true

        if (x < 4 && y > 2)
            if (grid[x][y] == grid[x+1][y-1] && grid[x][y] == grid[x+2][y-2] && grid[x][y] == grid[x+3][y-3])
                return true

        return false
    }

}

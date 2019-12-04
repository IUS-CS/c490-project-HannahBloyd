package com.example.finalproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.connect_four.*
import java.util.*


class ConnectFour : Fragment() {

    private val viewModel: FinalViewModel by lazy{
        ViewModelProviders.of(this).get(FinalViewModel::class.java) }

    private var winCounted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.connect_four, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val col0Button: Button by lazy { view.findViewById<Button>(R.id.col0Button) }
        val col1Button: Button by lazy { view.findViewById<Button>(R.id.col1Button) }
        val col2Button: Button by lazy { view.findViewById<Button>(R.id.col2Button) }
        val col3Button: Button by lazy { view.findViewById<Button>(R.id.col3Button) }
        val col4Button: Button by lazy { view.findViewById<Button>(R.id.col4Button) }
        val col5Button: Button by lazy { view.findViewById<Button>(R.id.col5Button) }
        val col6Button: Button by lazy { view.findViewById<Button>(R.id.col6Button) }

        score = 100


        col0Button.setOnClickListener{
            score--
            viewModel.play(0, 80F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }

        col1Button.setOnClickListener{
            score--
            viewModel.play(1, 230F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }

        col2Button.setOnClickListener{
            score--
            viewModel.play(2, 385F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }

        col3Button.setOnClickListener{
            score--
            viewModel.play(3, 535F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }

        col4Button.setOnClickListener{
            score--
            viewModel.play(4, 682F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }

        col5Button.setOnClickListener{
            score--
            viewModel.play(5, 835F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }

        col6Button.setOnClickListener{
            score--
            viewModel.play(6, 990F)
            cfView.invalidate()
            if (gameOver())
                endGame()
        }
    }

    fun endGame (){
        if (!winCounted) {
            winCounted = !winCounted
            val user = ScoreEntry(winner.toString(), score)
            viewModel.writeNewUser(user, "CF")
        }
        viewModel.updateGame("ConnectFour")
        callbacks?.endConnectFour()

    }

    fun gameOver (): Boolean{
        if (winner != 0)
            return true
        for (x in 0..6){
            for (y in 0..5){
                if (viewModel.grid[x][y] == 0){
                    break
                }
            }
        }

        return false
    }

    companion object {
        fun newInstance() = ConnectFour()
    }

    interface Callbacks {

        fun endConnectFour()

    }

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

}
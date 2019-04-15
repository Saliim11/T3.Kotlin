package com.saliim.hondat3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    var activePlayer = 1

    var score1 = 0
    var score2 = 0

    var boardWidth: Int = 0
    var boardHeight: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Tic Tac Toe"

        setT3Button()

        reset.setOnClickListener {
            resetDialog()

        }

    }

    private fun resetDialog(){
        val builder = AlertDialog.Builder(this@MainActivity)

        builder.setTitle("Reset the Game")
        builder.setMessage("Player 1 : $score1\nPlayer 2 : $score2\nIt'll Reset All Your Score, Are You Sure?")
        builder.setPositiveButton("YES"){dialog, which ->
            restartGame()
            score1 = 0
            score2 = 0
        }
        builder.setNegativeButton("NO, I want Continue"){dialog, which ->
            restartGame()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun setT3Button() {
        var board = imageView


        var vto = board.viewTreeObserver
        vto.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                boardWidth = board.measuredWidthAndState
                boardHeight = board.measuredHeightAndState

                return true
            }
        })

    }

    fun t3Click(view: View){
        val btnSelected = view as ImageView
        var cellId = 0

        when(btnSelected.id){
            R.id.btn_1 -> cellId = 1
            R.id.btn_2 -> cellId = 2
            R.id.btn_3 -> cellId = 3
            R.id.btn_4 -> cellId = 4
            R.id.btn_5 -> cellId = 5
            R.id.btn_6 -> cellId = 6
            R.id.btn_7 -> cellId = 7
            R.id.btn_8 -> cellId = 8
            R.id.btn_9 -> cellId = 9
        }

        playGame(cellId, btnSelected)

    }



    private fun playGame(cellId: Int, btnSelected: ImageView) {

        if (activePlayer == 1){
            btnSelected.setImageResource(R.drawable.x)
            player_turn.text = "Player 2 Turn"
            player1.add(cellId)
            activePlayer = 2

        } else {
//            btnSelected.text = "O"
            btnSelected.setImageResource(R.drawable.o)
            player2.add(cellId)
            player_turn.text = "Player 1 Turn"
            activePlayer = 1
        }
        btnSelected.isEnabled = false

        checkWinner()

    }

    private fun restartGame(){
        player1 = ArrayList()
        player2 = ArrayList()

        player1.clear()
        player2.clear()

        btn_1.setImageDrawable(null)
        btn_2.setImageDrawable(null)
        btn_3.setImageDrawable(null)
        btn_4.setImageDrawable(null)
        btn_5.setImageDrawable(null)
        btn_6.setImageDrawable(null)
        btn_7.setImageDrawable(null)
        btn_8.setImageDrawable(null)
        btn_9.setImageDrawable(null)

        btn_1.isEnabled = true
        btn_2.isEnabled = true
        btn_3.isEnabled = true
        btn_4.isEnabled = true
        btn_5.isEnabled = true
        btn_6.isEnabled = true
        btn_7.isEnabled = true
        btn_8.isEnabled = true
        btn_9.isEnabled = true


    }

    private fun gameOff() {
        btn_1.isEnabled = false
        btn_2.isEnabled = false
        btn_3.isEnabled = false
        btn_4.isEnabled = false
        btn_5.isEnabled = false
        btn_6.isEnabled = false
        btn_7.isEnabled = false
        btn_8.isEnabled = false
        btn_9.isEnabled = false
    }

    private fun checkWinner() {
        var winner = -1

        //winning 123
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //winning 456
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //winning 789
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //winning 147
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //winning 258
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }

        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //winning 369
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //winning 159
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        //winning 357
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }

        if (player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }

        if (winner != -1){
            if (winner == 1){
                Toast.makeText(this@MainActivity, "Player 1 Won", Toast.LENGTH_LONG).show()
                activePlayer = 2
                gameOff()

                score1++

                val builder = AlertDialog.Builder(this@MainActivity)

                builder.setTitle("Player 1 Won the Game")
                builder.setMessage("Player 1 : $score1\nPlayer 2 : $score2\nDo You Still Want to Continue?")
                builder.setPositiveButton("YES"){dialog, which ->
                    restartGame()
                }
                builder.setNegativeButton("NO"){dialog, which ->
                    resetDialog()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()

            } else {
                Toast.makeText(this@MainActivity, "Player 2 Won", Toast.LENGTH_LONG).show()
                activePlayer = 1
                gameOff()

                score2++

                val builder = AlertDialog.Builder(this@MainActivity)

                builder.setTitle("Player 2 Won the Game")
                builder.setMessage("Player 1 : $score1\nPlayer 2 : $score2\nDo You Still Want to Continue?")
                builder.setPositiveButton("YES"){dialog, which ->
                    restartGame()

                }
                builder.setNegativeButton("NO"){dialog, which ->
                    resetDialog()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }

    }

}

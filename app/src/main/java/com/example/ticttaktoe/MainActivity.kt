package com.example.ticttaktoe

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

lateinit var board: Array<Array<Button>>

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var PLAYER = true
    var turnCount = 0
    var status = Array(3) {
        IntArray(3)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board = arrayOf(
            arrayOf(button1, button2, button3),
            arrayOf(button4, button5, button6),
            arrayOf(button7, button8, button9)
        )


        for (i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initialise()
        btnrReset.setOnClickListener {
            PLAYER = true
            turnCount = 0
            initialise()
        }


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> {
                updateValue(row = 0, col = 0, player = PLAYER)
            }
            R.id.button2 -> {
                updateValue(row = 0, col = 1, player = PLAYER)
            }
            R.id.button3 -> {
                updateValue(row = 0, col = 2, player = PLAYER)
            }
            R.id.button4 -> {
                updateValue(row = 1, col = 0, player = PLAYER)
            }
            R.id.button5 -> {
                updateValue(row = 1, col = 1, player = PLAYER)
            }
            R.id.button6 -> {
                updateValue(row = 1, col = 2, player = PLAYER)
            }
            R.id.button7 -> {
                updateValue(row = 2, col = 0, player = PLAYER)
            }
            R.id.button8 -> {
                updateValue(row = 2, col = 1, player = PLAYER)
            }
            R.id.button9 -> {
                updateValue(row = 2, col = 2, player = PLAYER)
            }

        }
        turnCount++
        PLAYER = !PLAYER
        if (PLAYER) {
            updateDisplay("PLAYER X TURN")
        } else {
            updateDisplay("PLAYER 0 TURN")
        }
        if (turnCount == 9) {
            updateDisplay("GAME OVER BRO!")
        }
        checkWinner()


    }

    private fun checkWinner() {
        //row winner
        for (i in 0..2) {
            if (status[i][0] == status[i][1] && status[i][0] == status[i][2]) {
                if (status[i][0] == 1) {
                    updateDisplay("Player X won!")
                    break
                }
                if(status[i][0] == 0){
                    updateDisplay("Player 0 won!")
                    break
                }

            }


        }
         // col winner
        for (i in 0..2) {
            if (status[0][i] == status[1][i] && status[0][i] == status[2][i]) {
                if (status[0][i] == 1) {
                    updateDisplay("Player X won!")
                    break
                }
                if (status[0][i] == 0) {
                    updateDisplay("Player 0 won!")
                    break
                }
            }
        }
        //diagonal1
        if(status[0][0] == status[1][1] && status[1][1] == status[2][2]){
            if (status[0][0] == 1) {
                updateDisplay("Player X won!")
            }
            if (status[0][0] == 0) {
                updateDisplay("Player 0 won!")
            }
        }

        //diagonal2
        if(status[0][2] == status[1][1] && status[1][1] == status[2][0]){
            if (status[1][1] == 1) {
                updateDisplay("Player X won!")
            }
            if (status[1][1] == 0) {
                updateDisplay("Player 0 won!")
            }
        }

    }

    private fun updateDisplay(text: String) {
        textView.setText(text)
        if(text.contains("won")){
            for (i in board) {
                for (button in i) {
                    button.isEnabled = false
                }
            }
        }

    }

    private fun initialise() {
        for (i in 0..2) {
            for (j in 0..2) {
                status[i][j] = -1
            }
        }
        for (i in board) {
            for (button in i) {
                button.isEnabled = true
                button.setText("")
            }
        }

    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        var text: String = if (player) "X" else "0"
        val value = if (player) 1 else 0

        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        status[row][col] = value

    }
}
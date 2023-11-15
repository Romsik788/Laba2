package com.roman.lab1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class GameService : Service() {
    private val binder = GameBinder()
    inner class GameBinder : Binder() {
        fun getService(): GameService {
            return this@GameService
        }

        fun makeEnemyMove(board: List<SquareState>?): Int? {
            val emptySquares = board?.mapIndexedNotNull { index, squareState ->
                if (squareState == SquareState.EMPTY) index else null
            }

            return emptySquares?.takeIf { it.isNotEmpty() }?.random()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
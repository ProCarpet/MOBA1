package com.example.conwaysgameoflife

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private lateinit var gameField : Field

    init {
        // add callback
        holder.addCallback(this)
        // instantiate the game thread
        thread = GameThread(holder, this)
        // initalize the gameField

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        gameField = Field(50,50)
        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            retry = false
        }
    }

    /**
     * Function to update the field.
     */
    fun update() {
        gameField.update()
        // create new canvas and draw on it
    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        gameField.draw(canvas)
        super.draw(canvas)
    }

}
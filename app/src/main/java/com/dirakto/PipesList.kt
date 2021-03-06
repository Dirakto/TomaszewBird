package com.dirakto

import android.content.Context
import android.graphics.*
import android.widget.TextView
import kotlin.random.Random

class PipesList(val pipe:Bitmap, val pipeTop: Bitmap, private val mySurfaceView: MySurfaceView) {

    private val context: NewGame = mySurfaceView.context as NewGame
    var activatePipes = false
    val distance = 350
    val gap: Int = (mySurfaceView.tomaszew!!.tomaszewNormal.height+mySurfaceView.tomaszew!!.tomaszewNormal.height*3)

    var score: Int = 0

    var pipes: MutableList<Rect>? =  MutableList(1+mySurfaceView.width/(distance+pipe.width)) { i ->
        Rect(i*(distance+pipe.width)+mySurfaceView.width,
                Random.nextInt((mySurfaceView.height*0.752*0.4).toInt(), (mySurfaceView.height*0.752*0.78).toInt()),
            i*(pipe.width+distance)+pipe.width+mySurfaceView.width,
            (mySurfaceView.height*0.752).toInt())
    }

    fun updateCanvas(canvas: Canvas, paint: Paint){
        if(activatePipes)
            pipes!!.forEach {
                canvas.drawBitmap(pipe, null, it, paint)
                canvas.drawBitmap(pipeTop, null, Rect(it.left, 0, it.right, it.top-gap), paint)
            }
    }

    fun updatePipes(){
        val tmpRect = RectF(mySurfaceView.width*0.25f,
                                 mySurfaceView.tomaszew!!.tomaszewHeight,
                           mySurfaceView.width*0.25f+mySurfaceView.tomaszew!!.currentTomaszew.width,
                         mySurfaceView.tomaszew!!.tomaszewHeight+mySurfaceView.tomaszew!!.currentTomaszew.height)

        pipes!!.forEach {
            it.offset(-10, 0)
            if(it.left < tmpRect.left && it.right > tmpRect.right){
                score++
                context.updateScore(score.toString())
                if(score > context.highestScore!!){
                    var sharedPref = context.getSharedPreferences(context.getString(R.string.file), Context.MODE_PRIVATE)?: return
                    sharedPref.edit().putInt(context.resources.getString(R.string.high_score), score).apply()
                }

            }
            if(tmpRect.intersect(RectF(it)) || tmpRect.intersect(RectF(it.left.toFloat(), 0f, it.right.toFloat(), it.top-gap.toFloat())))
                mySurfaceView.myThread?.cancel(true)
        }

        if(pipes!![0].left <= 0){
            val tmp = pipes!!.last()
            pipes!!.add(Rect(tmp.right+distance,
                                  Random.nextInt((mySurfaceView.height*0.752*0.4).toInt(), (mySurfaceView.height*0.752*0.78).toInt()),
                            tmp.right+distance+pipe.width,
                                  (mySurfaceView.height*0.752).toInt()))
        }
        if(pipes!![0].right <= 0)
            pipes!!.removeAt(0)
    }
}
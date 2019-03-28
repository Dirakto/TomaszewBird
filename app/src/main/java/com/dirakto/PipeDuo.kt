package com.dirakto

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import kotlin.random.Random

class PipeDuo(i: Int, val pipesList: PipesList, mySurfaceView: MySurfaceView) {

    private val lowerPipe: RectF = RectF(i*(pipesList.distance+pipesList.pipe.width).toFloat()+mySurfaceView.width,
                                              Random.nextInt((mySurfaceView.height*0.752*0.4).toInt(), (mySurfaceView.height*0.752*0.78).toInt()).toFloat(),
                                        i*(pipesList.pipe.width+pipesList.distance)+pipesList.pipe.width+mySurfaceView.width.toFloat(),
                                      mySurfaceView.height*0.752f)
    private val upperPipe: RectF = RectF(lowerPipe.left, 0f, lowerPipe.right, lowerPipe.top-pipesList.gap)

    fun offset(dx: Float, dy: Float){
        lowerPipe.offset(dx, dy)
        upperPipe.offset(dx, dy)
    }

    fun drawPipes(canvas: Canvas, paint: Paint){
        canvas.drawBitmap(pipesList.pipe, null, lowerPipe, paint)
        canvas.drawBitmap(pipesList.pipeTop, null, upperPipe, paint)
    }


}
package com.dirakto

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.os.AsyncTask

class TomaszewBird(tomaszew: Bitmap,tomaszewDown: Bitmap, private val mySurfaceView: MySurfaceView) {

    val tomaszewNormal: Bitmap = Bitmap.createScaledBitmap(tomaszew, tomaszew.width / mySurfaceView.scale, tomaszew.height / mySurfaceView.scale, true)
    val tomaszewWing: Bitmap =  Bitmap.createScaledBitmap(tomaszewDown, tomaszewDown.width / mySurfaceView.scale, tomaszewDown.height / mySurfaceView.scale, true)

    var tomaszewHeight = mySurfaceView.height*0.4f
    private var drop = 1f
    private var myMatrix: Matrix = Matrix()

    var currentTomaszew: Bitmap = tomaszewNormal

    private var isUp = false
    private var timer = 0


    init {
        myMatrix.setTranslate(mySurfaceView.width*0.25f, tomaszewHeight)
    }


    fun updateCanvas(canvas: Canvas, paint: Paint){
        canvas.drawBitmap(currentTomaszew, myMatrix, paint)
    }

    fun updateBird(){
        if(timer == 0) {
            isUp = false
            drop = 1f
        }

        if(isUp) {
            timer--
            currentTomaszew = tomaszewWing

            if (tomaszewHeight - drop > 0) {
                drop += 5
                tomaszewHeight -= drop
                myMatrix.setTranslate(mySurfaceView.width * 0.25f, tomaszewHeight - drop)
            } else
                myMatrix.setTranslate(mySurfaceView.width * 0.25f, 0f)


        }else{
            currentTomaszew = tomaszewNormal
            drop *= 10
            tomaszewHeight += drop
            myMatrix.setTranslate(mySurfaceView.width * 0.25f, tomaszewHeight + drop)
        }

        if(tomaszewHeight+currentTomaszew.height.toFloat() >= (mySurfaceView.height*0.752f)){
            mySurfaceView.myThread?.cancel(true)
        }
    }

    fun onTouchAction(){
        if(mySurfaceView.myThread?.status == AsyncTask.Status.PENDING) {
            mySurfaceView.myThread?.execute()
        }else{
            isUp = true
            timer = 6
            drop = 15f
        }
    }

}
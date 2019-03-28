package com.dirakto

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

class Background(private val background: Bitmap, private val right: Int, private val bottom: Int) {

    private val backgroundRect: Rect = Rect(0,0, right*2, bottom)
    private val secondBackgroundRect: Rect = Rect(right-10, 0, right*3, bottom)
    private var activateSecondPicture = false


    fun updateBackgroud(){
        backgroundRect.offset(-15, 0)

        if(backgroundRect.right <= this.right) {
            activateSecondPicture = true
            secondBackgroundRect.offset(-15, 0)
        }
        if((secondBackgroundRect.right <= right)and(backgroundRect.right < 0)){
            backgroundRect.left = right-40
            backgroundRect.right = right*3
//            backgroundRect = Rect(right-40, 0, right*3, bottom)
        }
        if(secondBackgroundRect.right <= this.right){
            secondBackgroundRect.offset(-15, 0)
        }
        if(secondBackgroundRect.right < 0) {
            activateSecondPicture = false
            secondBackgroundRect.left = right - 40
            secondBackgroundRect.right = right*3
//            secondBackgroundRect = Rect(right-40, 0, right * 3, bottom)
        }
    }

    fun updateCanvas(canvas: Canvas, paint: Paint){
        canvas.drawBitmap(background, null, backgroundRect, paint)
        if(activateSecondPicture) {
            canvas.drawBitmap(background, null, secondBackgroundRect, paint)
        }
    }
}
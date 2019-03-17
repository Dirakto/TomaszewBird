package com.dirakto

import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView(context: Context): SurfaceView(context), SurfaceHolder.Callback{

    var myThread: MyThread? = null


    init{
        holder.addCallback(this)
//        myThread = MyThread()

//        this.setFocusable(true)


    }



    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {}

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }
    fun update(){

    }

//    override fun draw(canvas: Canvas?) {
//        super.draw(canvas)
//    }

}
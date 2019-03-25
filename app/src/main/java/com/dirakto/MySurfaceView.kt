package com.dirakto

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.AsyncTask
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView: SurfaceView, SurfaceHolder.Callback{

    var myThread: MyAsyncTask? = null

    constructor(context: Context): super(context){
        this.holder.addCallback(this)
        this.setFocusable(true)
        setWillNotDraw(false)
    }
    constructor(context: Context, attSet: AttributeSet): super(context, attSet){
        this.holder.addCallback(this)
        this.setFocusable(true)
        setWillNotDraw(false)
    }
    constructor(context: Context, attSet: AttributeSet, int: Int): super(context, attSet, int){
        this.holder.addCallback(this)
//        myThread = MyAsyncTask()
        this.setFocusable(true)
        setWillNotDraw(false)
    }
    constructor(context: Context, attSet: AttributeSet, int1: Int, int2: Int): super(context, attSet, int1, int2){
        this.holder.addCallback(this)
//        myThread = MyAsyncTask()
        this.setFocusable(true)
        setWillNotDraw(false)
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
//        Log.i()
//        myThread?.notCancelled = false;
//        myThread?.cancel(true)
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        myThread = MyAsyncTask(this.holder, this)
            myThread?.execute()
//        holder?.apply {
//            val canvas = lockCanvas()
//
//            canvas.drawCircle(100f, 100f, 100f, Paint())
//            unlockCanvasAndPost(canvas)
//        }
//        this.requestLayout()


    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
            canvas?.drawColor(Color.WHITE)

            val oldBitmap = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
            val scale = 1
            val bitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.width / scale, oldBitmap.height / scale, true)
            var matrix = Matrix()
            matrix.setRotate(50f, bitmap.width / 2f, bitmap.height / 2f)
            canvas?.drawBitmap(bitmap, matrix, Paint())
    }

//    fun update(){
//
//    }

}
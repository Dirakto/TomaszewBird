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

    val scale = 5
    var bg: Bitmap? = null
    var sun: Bitmap? = null
    var tomaszew: Bitmap? = null
    var pipe: Bitmap? = null


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
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        myThread = MyAsyncTask(this.holder, this)
        myThread?.execute()

//        val bgR =
        bg = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pixels))

//        val sunR =
        sun = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.sun))

        val tomaszewR = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
        tomaszew = Bitmap.createScaledBitmap(tomaszewR, tomaszewR.width / scale, tomaszewR.height / scale, true)

        pipe =  Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pipe))
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


//         val bg = BitmapFactory.decodeResource(resources, R.drawable.pixels)
//         val bitmap2 = Bitmap.createBitmap(bg)
        canvas?.drawBitmap(bg, null, Rect(0,0, this.right*2, this.bottom), Paint())

//            val sun = BitmapFactory.decodeResource(resources, R.drawable.sun)
//            val bitmap3 = Bitmap.createBitmap(sun)
//            Log.i("xd", this.right.toString())
         canvas?.drawBitmap(sun, null, Rect(this.right-60*scale, 0,this.right, 60*scale), Paint())

        canvas?.drawBitmap(pipe, null, Rect(1000, 0, 1100+pipe!!.width, (this.height*0.752).toInt()), Paint())
//            val oldBitmap = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
//            val bitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.width / scale, oldBitmap.height / scale, true)
         var matrix = Matrix()
//       matrix.setRotate(50f, bitmap.width / 2f, bitmap.height / 2f)
         canvas?.drawBitmap(tomaszew, matrix, Paint())
    }

    fun update(){

    }

}
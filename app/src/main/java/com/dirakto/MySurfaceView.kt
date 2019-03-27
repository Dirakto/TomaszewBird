package com.dirakto

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.AsyncTask
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class MySurfaceView: SurfaceView, SurfaceHolder.Callback{

    var myThread: MyAsyncTask? = null

    val scale = 5
    var bg: Bitmap? = null
    var sun: Bitmap? = null
    var tomaszew: Bitmap? = null
    var pipe: Bitmap? = null
    val paint = Paint()

    var tomaszewHeight = 0f
    var drop = 1f
    var myMatrix: Matrix = Matrix()

    var isReady = false
    var isUp = false
    var timer = 0


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
        this.setFocusable(true)
        setWillNotDraw(false)
    }
    constructor(context: Context, attSet: AttributeSet, int1: Int, int2: Int): super(context, attSet, int1, int2){
        this.holder.addCallback(this)
        this.setFocusable(true)
        setWillNotDraw(false)
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {}

    override fun surfaceCreated(holder: SurfaceHolder?) {
        tomaszewHeight = this.height*0.4f
        myMatrix.setTranslate(this.width*0.25f, tomaszewHeight)
        myMatrix.setRotate(30f)
        bg = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pixels))
        sun = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.sun))
        val tomaszewR = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
        tomaszew = Bitmap.createScaledBitmap(tomaszewR, tomaszewR.width / scale, tomaszewR.height / scale, true)
        pipe =  Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pipe))

//        var canvas = holder?.lockCanvas()
//        canvas?.drawColor(Color.WHITE)
//        canvas?.drawBitmap(bg!!, null, Rect(0,0, this.right*2, this.bottom), paint)
//        canvas?.drawBitmap(sun!!, null, Rect(this.right-60*scale, 0,this.right, 60*scale), paint)
//        canvas?.drawBitmap(tomaszew!!, myMatrix, paint)
//        holder?.unlockCanvasAndPost(canvas)

        myThread = MyAsyncTask(this.holder, this)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.WHITE)

        canvas?.drawBitmap(bg!!, null, Rect(0,0, this.right*2, this.bottom), paint)

        canvas?.drawBitmap(sun!!, null, Rect(this.right-60*scale, 0,this.right, 60*scale), paint)

//            val oldBitmap = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
//            val bitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.width / scale, oldBitmap.height / scale, true)
//       matrix.setRotate(50f, bitmap.width / 2f, bitmap.height / 2f)
         canvas?.drawBitmap(tomaszew!!, myMatrix, paint)
    }

    fun update(){
        if(timer == 0) {
            isUp = false
            drop = 1f
        }
        if(isUp){
            timer--
            drop = drop + 5
            tomaszewHeight -= drop
            myMatrix.setRotate(30f)
            myMatrix.setTranslate(this.width*0.25f, tomaszewHeight-drop)
        }else {
//            drop = drop * 10
//            tomaszewHeight += drop
//            myMatrix.setTranslate(this.width * 0.25f, tomaszewHeight + drop)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(!isReady) {
            isReady = true
            myThread?.execute()
        }else{
            isUp = true
            timer = 4
            drop = 15f
        }
        return super.onTouchEvent(event)
    }

}
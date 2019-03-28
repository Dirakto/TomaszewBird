package com.dirakto

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.random.Random

class MySurfaceView: SurfaceView, SurfaceHolder.Callback{

    var myThread: MyAsyncTask? = null

    val scale = 5
    var bg: Bitmap? = null
//    var bg2: Bitmap? = null
    var sun: Bitmap? = null
    var tomaszew: Bitmap? = null
    var tomaszewDown: Bitmap? = null
    var pipe: Bitmap? = null
    var pipeTop: Bitmap? = null
    val paint = Paint()

    var birdImage: Bitmap? = null
    var tomaszewHeight = 0f
    var drop = 1f
    var myMatrix: Matrix = Matrix()

    var isReady = false
    var isUp = false
    var timer = 0

    var backgroundRect: Rect? = null
    var secondBackgroundRect: Rect? = null
    var activateSecondPicture = false

    var pipeStarter = 150
    var pipes: MutableList<Rect>? = null
    var activatePipes = false
    val distance = 350
    var gap: Int? = null


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

        bg = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.background))
        backgroundRect = Rect(0,0, this.right*2, this.bottom)
        secondBackgroundRect = Rect(this.right-10, 0, this.right*3, this.bottom)

        sun = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.sun))
        val tomaszewR = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
        tomaszew = Bitmap.createScaledBitmap(tomaszewR, tomaszewR.width / scale, tomaszewR.height / scale, true)
        val tomaszewDownR = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_down)
        tomaszewDown = Bitmap.createScaledBitmap(tomaszewDownR, tomaszewR.width / scale, tomaszewR.height / scale, true)
        birdImage = tomaszew
        pipe =  Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pipe))
        pipeTop = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pipe_top))
        gap = (tomaszew!!.height+tomaszew!!.height*3)

        pipes = MutableList(1+this.width/(distance+pipe!!.width)) { i ->
            Rect(i*(distance+pipe!!.width)+this.width, Random.nextInt((this.height*0.752*0.4).toInt(), (this.height*0.752*0.78).toInt()), i*(pipe!!.width+distance)+pipe!!.width+this.width,(this.height*0.752).toInt())
         }
//        pipes!!.forEach {
//            Log.i("XD", it.left.toString()+" "+it.top+" "+it.right)
//        }

        myThread = MyAsyncTask(this.holder, this)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.WHITE)

        canvas?.drawBitmap(bg!!, null, backgroundRect, paint)
        if(activateSecondPicture) {
            canvas?.drawBitmap(bg!!, null, secondBackgroundRect, paint)
        }

        canvas?.drawBitmap(sun!!, null, Rect(this.right-60*scale, 0,this.right, 60*scale), paint)

//            val oldBitmap = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
//            val bitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.width / scale, oldBitmap.height / scale, true)
//       matrix.setRotate(50f, bitmap.width / 2f, bitmap.height / 2f)
         canvas?.drawBitmap(birdImage!!, myMatrix, paint)

        if(activatePipes) {
            pipes!!.forEach {
                canvas?.drawBitmap(pipe, null, it, paint)
                canvas?.drawBitmap(pipeTop, null, Rect(it.left, 0, it.right, it.top-gap!!), paint)
            }
        }
    }

    fun update(){
        updateBird()
        updateBackgroud()

        if(pipeStarter>0)
            pipeStarter--
        else {
            activatePipes = true
            updatePipes()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(!isReady) {
            isReady = true
            myThread?.execute()
        }else{
            isUp = true
            timer = 6
            drop = 15f
        }
        return super.onTouchEvent(event)
    }

    private fun updateBird(){
        if(timer == 0) {
            isUp = false
            drop = 1f
        }

        if(isUp) {
            timer--

                if (tomaszewHeight - drop > 0) {
                    drop += 5
                    tomaszewHeight -= drop
                    myMatrix.setTranslate(this.width * 0.25f, tomaszewHeight - drop)
                } else
                    myMatrix.setTranslate(this.width * 0.25f, 0f)


                birdImage = tomaszewDown

            if(timer == 0)
                Thread.sleep(50)
        }else{
            birdImage = tomaszew
            drop *= 10
            tomaszewHeight += drop
            myMatrix.setTranslate(this.width * 0.25f, tomaszewHeight + drop)
        }

        if(tomaszewHeight+birdImage?.height!!.toFloat() >= (this.height*0.752f)){
            myThread?.cancel(true)
        }
    }

    fun updateBackgroud(){
        backgroundRect?.offset(-15, 0)

        if(backgroundRect!!.right <= this.right) {
            activateSecondPicture = true
            secondBackgroundRect!!.offset(-15, 0)
        }
        if((secondBackgroundRect!!.right <= this.right)and(backgroundRect!!.right < 0)){
            backgroundRect = Rect(this.right-40, 0, this.right*3, this.bottom)
        }
        if(secondBackgroundRect!!.right <= this.right){
            secondBackgroundRect!!.offset(-15, 0)
        }
        if(secondBackgroundRect!!.right < 0) {
            activateSecondPicture = false
            secondBackgroundRect = Rect(this.right-40, 0, this.right * 3, this.bottom)
        }
    }

    fun updatePipes(){
        val tmpRect = RectF(this.width*0.25f, tomaszewHeight, this.width*0.25f+birdImage!!.width, tomaszewHeight+birdImage!!.height)
        pipes!!.forEach {
            it.offset(-10, 0)
            if(tmpRect.intersect(RectF(it)) || tmpRect.intersect(RectF(it.left.toFloat(), 0f, it.right.toFloat(), it.top-gap!!.toFloat())))
                myThread?.cancel(true)
        }
        if(pipes!![0].left <= 0){
            val tmp = pipes!!.last()
            pipes!!.add(Rect(tmp.right+distance, Random.nextInt((this.height*0.752*0.4).toInt(), (this.height*0.752*0.78).toInt()), tmp.right+distance+pipe!!.width,(this.height*0.752).toInt()))
        }
        if(pipes!![0].right <= 0)
            pipes!!.removeAt(0)
    }

}
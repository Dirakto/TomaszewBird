package com.dirakto

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import kotlin.random.Random

class MySurfaceView: SurfaceView, SurfaceHolder.Callback{

    var myThread: MyAsyncTask? = null
    var textArea: TextView? = null

    val scale = 5

    private var sun: Bitmap? = null

    private val paint = Paint()


    var tomaszew: TomaszewBird? = null
    var background: Background? = null
    var pipeList: PipesList? = null


    private var pipeStarter = 150


    constructor(context: Context): super(context){
        this.holder.addCallback(this)
        this.setFocusable(true)
        setWillNotDraw(false)
        textArea = (context as Activity).findViewById(R.id.textView2)
    }
    constructor(context: Context, attSet: AttributeSet): super(context, attSet){
        this.holder.addCallback(this)
        this.setFocusable(true)
        setWillNotDraw(false)
        textArea = (context as Activity).findViewById(R.id.textView2)
    }
    constructor(context: Context, attSet: AttributeSet, int: Int): super(context, attSet, int){
        this.holder.addCallback(this)
        this.setFocusable(true)
        setWillNotDraw(false)
        textArea = (context as Activity).findViewById(R.id.textView2)
    }


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder?) {}

    override fun surfaceCreated(holder: SurfaceHolder?) {


        tomaszew = TomaszewBird(BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b),
                                BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_down), this)
        background = Background(Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.background)), this.right, this.bottom)


        sun = Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.sun))

        pipeList = PipesList(Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pipe)),
                             Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.pipe_top)), this)

        myThread = MyAsyncTask(this.holder, this)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.WHITE)

        background?.updateCanvas(canvas!!, paint)

        canvas?.drawBitmap(sun!!, null, Rect(this.right-60*scale, 0,this.right, 60*scale), paint)

        tomaszew?.updateCanvas(canvas!!, paint)

        pipeList?.updateCanvas(canvas!!, paint)

    }

    fun update(){
        tomaszew?.updateBird()
        background?.updateBackgroud()


        if(pipeStarter>0)
            pipeStarter--
        else {
            pipeList?.activatePipes = true
            pipeList?.updatePipes()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
            tomaszew?.onTouchAction()
        return super.onTouchEvent(event)
    }





}
package com.dirakto

import android.graphics.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import kotlinx.android.synthetic.main.activity_new_game.*

import android.graphics.drawable.BitmapDrawable
import android.view.SurfaceView
import android.view.Window
import android.view.WindowManager

class NewGame : AppCompatActivity(), SurfaceHolder.Callback{

    var surfaceView: SurfaceView? = null
    var surfaceHolder: SurfaceHolder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        this.requestWindowFeature(Window.FEATURE_);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_new_game)

        surfaceView = findViewById(R.id.surfaceView)
        surfaceHolder = surfaceView!!.holder
        surfaceHolder!!.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        val oldBitmap = BitmapFactory.decodeResource(resources, R.drawable.tomaszew_bird32_b)
        val scale = 4
        val bitmap = Bitmap.createScaledBitmap(oldBitmap, oldBitmap.width/scale, oldBitmap.height/scale, true)
        val canvas = surfaceHolder!!.lockCanvas()

//        canvas.drawOval(50f, 50f, 500f, 500f, Paint().apply { color = Color.BLACK })
//        canvas.drawBitmap(bitmap, 0f, 0f, Paint())
        var matrix = Matrix()
        matrix.setRotate(50f, bitmap.width/2f, bitmap.height/2f)
        canvas.drawBitmap(bitmap, matrix, Paint())

        surfaceHolder!!.unlockCanvasAndPost(canvas)
        surfaceView!!.requestLayout()

        surfaceView!!.invalidate()
    }


}


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

class NewGame : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_game)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findViewById<MySurfaceView>(R.id.mySurfaceView).myThread?.cancel(true)
    }

}


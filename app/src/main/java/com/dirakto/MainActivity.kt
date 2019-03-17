package com.dirakto

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tomaszew_bird ma 22-24 pixle wysokosci, 37 pixle dlugosci
    }
    fun newGame(view: View){
        val intent = Intent(this@MainActivity, NewGame::class.java)
        startActivity(intent)
    }
    fun exit(view: View){
        this.finishActivity(0)
        this.moveTaskToBack(true)
    }
}


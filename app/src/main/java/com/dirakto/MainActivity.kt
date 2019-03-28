package com.dirakto

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity(){

    var textArea: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textArea = findViewById(R.id.textView3)
        loadHighScore()
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

    fun loadHighScore(){
        var sharedPref = this.getSharedPreferences(getString(R.string.file), Context.MODE_PRIVATE)?: return
        textArea?.setText(sharedPref.getInt(resources.getString(R.string.high_score), 0).toString())
    }

    override fun onResume(){
        super.onResume()
        loadHighScore()
    }

}


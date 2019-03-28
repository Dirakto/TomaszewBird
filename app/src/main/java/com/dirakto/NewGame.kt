package com.dirakto

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class NewGame : AppCompatActivity(), MyScoreCallback {

    var textArea: TextView? = null
    var highestScore: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_game)

        textArea = findViewById(R.id.textView2)
        val sharedPref = this.getSharedPreferences(getString(R.string.file), Context.MODE_PRIVATE)?: return
        highestScore = sharedPref.getInt(resources.getString(R.string.high_score), 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        findViewById<MySurfaceView>(R.id.mySurfaceView).myThread?.cancel(true)
    }

    override fun updateScore(txt: String){
        textArea?.setText(txt)
    }
}


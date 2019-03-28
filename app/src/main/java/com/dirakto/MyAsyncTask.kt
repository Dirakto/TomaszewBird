package com.dirakto

import android.graphics.Canvas
import android.os.AsyncTask
import android.util.Log
import android.view.SurfaceHolder

class MyAsyncTask: AsyncTask<Any, Any, Any> {


    var holder: SurfaceHolder? = null
    var view: MySurfaceView? = null

    val FPS: Long = 25

    constructor(holder: SurfaceHolder, view: MySurfaceView): super(){
        this.holder = holder
        this.view = view
    }

    override fun doInBackground(vararg params: Any?): Any {
        while(!isCancelled){
            publishProgress()
            Thread.sleep(1000L/FPS)
        }
        return "Done"
    }

    override fun onProgressUpdate(vararg values: Any?) {
        view?.update()
        view?.postInvalidate()
    }

    override fun onPreExecute() {
    }
}
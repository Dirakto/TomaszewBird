package com.dirakto

import android.graphics.Canvas
import android.os.AsyncTask
import android.util.Log
import android.view.SurfaceHolder

class MyAsyncTask: AsyncTask<Any, Any, Any> {

    var holder: SurfaceHolder? = null
    var view: MySurfaceView? = null
    var canvas: Canvas? = null

    val FPS: Long = 30

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
        Log.i("MyINFO", "DZIALAM")
        canvas = holder?.lockCanvas()
//        view?.update()
        view?.draw(canvas)
        holder?.unlockCanvasAndPost(canvas)
    }
    override fun onPreExecute() {
    }
}
package com.dirakto

import android.os.AsyncTask
import android.view.SurfaceHolder

class MyThread: AsyncTask<Any, Any, Any> {

    var i: Int = 10
    val FPS: Int = 30

    constructor(holder: SurfaceHolder, view: MySurfaceView): super(){
        var canvas = holder.lockCanvas()

        while(true){
            view.update()
            view.draw(canvas)
            holder.unlockCanvasAndPost(canvas)

            Thread.sleep((1000/FPS).toLong())

        }
    }

    override fun doInBackground(vararg params: Any?): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProgressUpdate(vararg values: Any?) {
//        values[0]?.let { progr.progress = it }
    }
    override fun onPreExecute() {
//        progr.visibility = View.VISIBLE
    }
}
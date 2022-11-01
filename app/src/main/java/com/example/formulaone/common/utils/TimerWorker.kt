package com.example.formulaone.common.utils

import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

private const val TAG = "BlurWorker"
class TimerWorker (context:Context, params:WorkerParameters): Worker(context,params){
    override fun doWork(): Result {
        val appContext = applicationContext

        return try {


        }catch (throwable: Throwable) {
            Log.e(TAG, "Error applying timer")
            Result.failure()
        }
    }

}
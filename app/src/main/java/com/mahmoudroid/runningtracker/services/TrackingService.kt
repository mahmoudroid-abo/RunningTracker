package com.mahmoudroid.runningtracker.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.mahmoudroid.runningtracker.other.Constants.ACTION_PAUSE_SERVICE
import com.mahmoudroid.runningtracker.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.mahmoudroid.runningtracker.other.Constants.ACTION_STOP_SERVICE
import timber.log.Timber

class TrackingService : LifecycleService() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent.let {
            when (it?.action) {
                ACTION_START_OR_RESUME_SERVICE ->
                    Timber.d("Started Or Resumed Service")
                ACTION_PAUSE_SERVICE ->
                    Timber.d("Pause Service")
                ACTION_STOP_SERVICE ->
                    Timber.d("Stop Service")
            }
        }
        return super.onStartCommand(intent, flags, startId)

    }
}
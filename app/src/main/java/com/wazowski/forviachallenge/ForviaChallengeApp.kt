package com.wazowski.forviachallenge

import android.app.Application
import com.wazowski.forviachallenge.notification.ForviaNotificationWorker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ForviaChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ForviaNotificationWorker.schedule(applicationContext)
    }
}

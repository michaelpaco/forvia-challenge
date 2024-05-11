package com.wazowski.forviachallenge.notification

import android.content.Context
import androidx.work.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

class ForviaNotificationWorker(
    private val appContext: Context, workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        ForviaNotificationHandler.createReminderNotification(appContext)
        return Result.success()
    }

    companion object {
        const val REMINDER_TAG = "forvia_reminder"
        fun schedule(appContext: Context) {
            val currentTime = Calendar.getInstance()
            val targetTime = Calendar.getInstance().apply {
                set(Calendar.MINUTE, 17)
                set(Calendar.SECOND, 0)
            }

            if (targetTime.before(currentTime)) {
                targetTime.add(Calendar.MINUTE, 30)
            }

            val initialDelayMillis = targetTime.timeInMillis - System.currentTimeMillis()

            val notificationRequest = PeriodicWorkRequestBuilder<ForviaNotificationWorker>(
                repeatInterval = 30, repeatIntervalTimeUnit = TimeUnit.MINUTES
            ).addTag(REMINDER_TAG).setInitialDelay(initialDelayMillis, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(appContext).enqueueUniquePeriodicWork(
                "reminder_notification_work",
                ExistingPeriodicWorkPolicy.UPDATE,
                notificationRequest
            )
        }
    }
}
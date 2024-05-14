package com.wazowski.forviachallenge.notification

import android.content.Context
import android.util.Log
import androidx.work.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ForviaNotificationWorker(
    private val appContext: Context, workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        ForviaNotificationHandler.createReminderNotification(appContext)
        return Result.success()
    }

    companion object {
        private const val REMINDER_TAG = "forvia_reminder"
        fun schedule(appContext: Context) {
            val currentTime = Calendar.getInstance()

            val targetTime = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 5) // Add 5 minutes to the current time
            }

            // Schedule next notification to 5 minutes after first app initialization
            // If the target time is in the past, move it to the next occurrence
            if (targetTime.before(currentTime)) {
                targetTime.add(Calendar.MINUTE, 30 - (currentTime.get(Calendar.MINUTE) % 30))
            }

            val initialDelayMillis = targetTime.timeInMillis - System.currentTimeMillis()
            val notificationRequest = PeriodicWorkRequestBuilder<ForviaNotificationWorker>(
                repeatInterval = 30, repeatIntervalTimeUnit = TimeUnit.MINUTES
            ).addTag(REMINDER_TAG).setInitialDelay(initialDelayMillis, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(appContext).enqueueUniquePeriodicWork(
                "reminder_notification_work", ExistingPeriodicWorkPolicy.UPDATE, notificationRequest
            )

            logNextSchedule(initialDelayMillis)
        }

        private fun logNextSchedule(initialDelayMillis: Long) {
            val nextScheduledTime = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis() + initialDelayMillis
            }
            val formattedTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                nextScheduledTime.time
            )
            Log.i(REMINDER_TAG, "Next scheduled work at: $formattedTime")
            Log.i(REMINDER_TAG, "Scheduled periodic work with tag: $REMINDER_TAG")
        }
    }
}
package com.wazowski.forviachallenge.notification

import android.annotation.SuppressLint
import android.app.*
import android.content.*
import androidx.core.app.*
import com.wazowski.forviachallenge.R
import com.wazowski.forviachallenge.presentation.MainActivity

object ForviaNotificationHandler {
    private const val CHANNEL_ID = "new_apps_reminder"

    private val notificationMessages = listOf(
        "Discover exciting new apps in the store. Tap to explore and find your next favorite app.",
        "Get the latest apps and updates. Explore now!",
        "Find amazing apps waiting for you. Check them out now!"
    )

    @SuppressLint("MissingPermission")
    fun createReminderNotification(context: Context) {
        createNotificationChannel(context)

        val builder = createBuilder((context))

        with(NotificationManagerCompat.from(context)) {
            notify(1, builder.build())
        }
    }

    private fun createBuilder(context: Context): NotificationCompat.Builder {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("New apps available!").setContentText(notificationMessages.random())
            .setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent)
            .setAutoCancel(true).setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }

    private fun createNotificationChannel(context: Context) {
        val name = "New Apps Reminders"
        val descriptionText =
            "This notification channel is used to nofity the user of new apps in our catalog."
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
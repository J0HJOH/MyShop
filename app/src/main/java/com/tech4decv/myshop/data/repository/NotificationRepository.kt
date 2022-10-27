package com.tech4decv.myshop.data.repository

import android.content.Context
import com.tech4decv.myshop.data.models.Notification
import com.tech4decv.myshop.data.notification_provider.NotificationFromSharedPreference

class NotificationRepository(context: Context) {
    private val notificationProvider = NotificationFromSharedPreference(context)

    fun getAllNotifications (): MutableList<Notification>{
        return notificationProvider.getAllNotifications()
    }

    fun deleteNotification(notification: Notification){
        notificationProvider.deleteNotification(notification)
    }

    fun saveNotification(notification: Notification) {
        notificationProvider.saveNotification(notification)
    }

}
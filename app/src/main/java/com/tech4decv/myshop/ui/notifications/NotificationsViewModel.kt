package com.tech4decv.myshop.ui.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4decv.myshop.data.models.Notification
import com.tech4decv.myshop.data.repository.NotificationRepository

class NotificationsViewModel(application: Application) : AndroidViewModel(application) {
    private val notificationsRepository = NotificationRepository(application)

    fun getAllNotifications() : MutableList<Notification>{
        return notificationsRepository.getAllNotifications()
    }

    fun deleteNotification(notification: Notification){
        return notificationsRepository.deleteNotification(notification)
    }

}
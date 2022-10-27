package com.tech4decv.myshop.data.notification_provider

import android.content.Context
import android.content.SharedPreferences
import com.tech4decv.myshop.data.models.Notification

class NotificationFromSharedPreference(context: Context) {
    private val sharedPreference = context.getSharedPreferences("NOTIFICATION",Context.MODE_PRIVATE)
    private val editor : SharedPreferences.Editor = sharedPreference.edit()

    fun getAllNotifications(): MutableList<Notification>{
        //declare list returned
        val listOfNotification = mutableListOf<Notification>()

        //get list of keys
        val listOfKeys : List<String> = sharedPreference.all.keys.toList()

        //for each key, create a corresponding notification object
        for(key in listOfKeys){
            val time : Long = key.toLong()
            val message :String = sharedPreference.getString(key, "") ?: ""
            val notification = Notification(time, message)

            //add to the returned list
            listOfNotification.add(notification)
        }
        //return list to user
        return listOfNotification

    }

    fun deleteNotification(notification: Notification){
        //call notification editor
        //remove from list according to key
        editor.remove(notification.time.toString())
        editor.commit()

    }

    fun saveNotification(notification: Notification) {
        editor.putString(
            notification.time.toString(),
            notification.message
        )
        editor.commit()
    }
}
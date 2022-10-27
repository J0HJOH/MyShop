package com.tech4decv.myshop.ui.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tech4decv.myshop.R
import com.tech4decv.myshop.data.models.Notification
import java.util.*

class NotificationAdapter(val context: Context, private val listOfNotification: MutableList<Notification>):
    RecyclerView.Adapter<NotificationViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_notifications,parent,false)

        return NotificationViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = listOfNotification[position]

        //shows time
        val date = Date(notification.time)
        holder.date.text = date.toString()
        //shows notification message
        holder.message.text = notification.message

        //setUp popUp menu
        holder.menuButton.setOnClickListener {
            showPopUpMenu(holder)
        }
    }

    private fun showPopUpMenu(holder: NotificationViewHolder) {
        val popUpMenu = PopupMenu(context, holder.menuButton)
        popUpMenu.inflate(R.menu.notification_menu)
        popUpMenu.show()

        popUpMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.delete_notification -> {
                    Toast.makeText(
                        context,
                        "Notification Deleted", Toast.LENGTH_LONG
                    ).show()
                    return@setOnMenuItemClickListener true
                }
            }
            false
        }
    }

    override fun getItemCount(): Int = listOfNotification.size

}

class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val date : TextView = itemView.findViewById(R.id.date)
    val message : TextView = itemView.findViewById(R.id.notification_message)
    val menuButton: ImageButton = itemView.findViewById(R.id.show_menu)


}
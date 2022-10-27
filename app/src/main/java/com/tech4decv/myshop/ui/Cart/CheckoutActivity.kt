package com.tech4decv.myshop.ui.Cart

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tech4decv.myshop.R
import com.tech4decv.myshop.data.models.Notification
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.databinding.ActivityCheckoutBinding
import java.util.*

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCheckoutBinding
    private lateinit var cartViewModel: CartViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize cartViewModel The ViewModelProvider is like a tracker for all viewModels
        //in the project...the (.get(specify the viewModel you want))
        cartViewModel = ViewModelProvider(this)[cartViewModel::class.java]

        showPaymentInfo()

        //set up spinner showing card name
        setUpCardSpinner()


        binding.makePayment.setOnClickListener{

            if(inputIsValid()){
                showConfirmDialog()
                showNotification(
                    message = "You just paid for a product worth $${cartViewModel.getPrice()}"
                )
                cartViewModel.clearCart()
            }

        }
    }

    private fun showNotification(message: String) {
        val id = "payment_successful"


        //Create notification channel(if sdk version is more than 8.0)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Declare variables
            val channelName = "Payment Confirmation"
            val description = " User just made a successful payment"
            val importance : Int = NotificationManager.IMPORTANCE_DEFAULT


            //created a notification channel
            val notificationChannel = NotificationChannel(id, channelName, importance)
            notificationChannel.description = description

            //Assigned a channel to the notification manager,
            // by /casting/ the notification service as a notification manager, we use /as/ for casting in kotlin
            val notificationManager = getSystemService(NOTIFICATION_SERVICE)as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }

        //Create a notification builder
        val builder = NotificationCompat.Builder(this, id)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("Payment Made")
        builder.setContentText(message)

        val notificationManagerCompat = NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(111,builder.build())

        saveNotification(message)
    }

    private fun saveNotification(message: String) {
        val notification = Notification(
            System.currentTimeMillis(),message
        )
        cartViewModel.saveNotification(notification)
    }

    private fun inputIsValid(): Boolean {
        val cardNumber = binding.cardNumber.text.toString()
        val cvv = binding.cvv.text.toString()

        return if(cardNumber.length<13 || cardNumber.length > 15){
            Toast.makeText(this,"Invalid Card Number",Toast.LENGTH_LONG).show()
            false
        }else if(cvv.length!=3){
            Toast.makeText(this,"Invalid CVV",Toast.LENGTH_LONG).show()
            false
        } else{
            true
        }
    }

    private fun showConfirmDialog() {
        AlertDialog.Builder(this)
            .setView(R.layout.layout_payment_successful)
            .setPositiveButton("Ok") { dialog, which ->
                this@CheckoutActivity.finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun setUpCardSpinner() {
        val listOfCardProviders = listOf<String>("Visa", "MasterCard", "Verve")
        binding.cardName.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOfCardProviders
        )
    }

    private fun showPaymentInfo() {
        // show subTotal
        val subTotal: Double = cartViewModel.getPrice()
        binding.subtotalPrice.text = "$${subTotal}"

        //show shipping Cost
        val shippingCost: Double = 0.01 * subTotal
        binding.shippingPrice.text = " $${shippingCost}"

        //show Total
        val total: Double = subTotal + shippingCost
        binding.totalPrice.text = "$${total}"
    }
}
package com.tech4decv.myshop.ui.Cart

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.tech4decv.myshop.R
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
                cartViewModel.clearCart()
            }

        }
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
            .setNegativeButton("Ok") { dialog, which ->
                this@CheckoutActivity.finish()
            }
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
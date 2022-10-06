package com.tech4decv.myshop.ui.productDetails

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.data.repository.CartRepository

class ProductDetailsViewModel() : ViewModel(){

    fun  saveToCart(products: Products){
        //add product repository
        CartRepository.addToCart(products)

    }
}
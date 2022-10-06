package com.tech4decv.myshop.ui.Cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.data.repository.CartRepository

class CartViewModel : ViewModel() {
    fun getProducts(): List<Products>{
        return CartRepository.getSelectedProducts().keys.toList()
    }

    fun increaseQuantity(){

    }
    fun decreaseQuantity(){

    }

}
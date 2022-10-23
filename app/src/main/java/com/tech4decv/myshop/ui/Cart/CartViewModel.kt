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
    fun getQuantity(products: Products) : Int{
        return CartRepository.getQuantity(products)
    }

    fun getCartLiveData(): MutableLiveData<MutableMap<Products, Int>> {
        return CartRepository.getCartLiveData()
    }

    fun increaseQuantity(products: Products){
        return CartRepository.increaseQuantity(products)

    }
    fun decreaseQuantity(products: Products){
        return CartRepository.reduceQuantity(products)

    }

    fun removeFromCart(products: Products){
        return CartRepository.removeFromCart(products)
    }

    fun getPrice(): Double{
        return  CartRepository.getPrice()
    }

    fun clearCart(){
        CartRepository.clearCart()
    }
}
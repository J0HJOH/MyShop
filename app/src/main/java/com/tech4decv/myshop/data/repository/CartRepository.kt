package com.tech4decv.myshop.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tech4decv.myshop.data.models.Products

object CartRepository {
    private val selectedProducts = mutableMapOf<Products, Int>()
    private  val cartLiveData  = MutableLiveData<MutableMap<Products,Int>>(selectedProducts)

    fun addToCart(products : Products){
        selectedProducts[products] = 1
    }

    fun getQuantity(products: Products): Int{
        return  selectedProducts[products]!!
    }

    fun removeFromCart(products: Products){
        selectedProducts.remove(products)
       liveDataUpdate()
    }

    fun reduceQuantity(products: Products){
        var quantity: Int = selectedProducts[products]!!
        quantity--
        selectedProducts[products] = quantity
        liveDataUpdate()
    }
    fun increaseQuantity(products: Products){
        var quantity:Int = selectedProducts[products]!!
        quantity++
        selectedProducts[products] = quantity

        // update the livedata quantity
        liveDataUpdate()
    }

    fun getPrice(): Double {
        var price: Double = 0.0
        for (items in selectedProducts.keys){
            val totalPrice = items.price!!.times(((selectedProducts[items]!!)))
            price += totalPrice
        }
        return price
    }
    fun getSelectedProducts() : Map<Products, Int>{
        return selectedProducts.toMap()
    }

    fun getCartLiveData(): MutableLiveData<MutableMap<Products, Int>> = cartLiveData

    private fun liveDataUpdate() {
        cartLiveData.value = selectedProducts
    }
}
package com.tech4decv.myshop.data.repository

import com.tech4decv.myshop.data.models.Products

object CartRepository {
    private val selectedProducts = mutableMapOf<Products, Int>()

    fun addToCart(products : Products){
        selectedProducts[products] = 1
    }

    fun removeFromCart(products: Products){
        selectedProducts.remove(products)
    }

    fun reduceQuantity(products: Products){
        var quantity: Int = selectedProducts[products]!!
        quantity--
        selectedProducts[products] = quantity
    }
    fun increaseQuantity(products: Products){
        var quantity:Int = selectedProducts[products]!!
        quantity++
        selectedProducts[products] = quantity
    }

    fun getPrice(): Double {
        var price: Double = 0.0
        for (items in selectedProducts.keys){
            price += items.price!!
        }
        return price
    }
    fun getSelectedProducts() : Map<Products, Int>{
        return selectedProducts.toMap()
    }


}
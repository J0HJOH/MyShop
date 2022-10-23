package com.tech4decv.myshop.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.tech4decv.myshop.data.favorite_provider.FavoriteProvider
import com.tech4decv.myshop.data.favorite_provider.SharedPreferenceFavorite
import com.tech4decv.myshop.data.firebase.ProductsDataSource
import com.tech4decv.myshop.data.models.Products

class FavoriteRepository(context: Context) {

    private val favoriteProvider: FavoriteProvider = SharedPreferenceFavorite(context)
    private val productsDataSource = ProductsDataSource()

    fun isFavorite(productId: String): Boolean {
        return  favoriteProvider.isFavorite(productId)

    }

    fun removeProduct(id: String) {
        return favoriteProvider.removeFavorite(id)
    }

    fun addProduct(id: String) {
        return favoriteProvider.addFavorite(id)
    }

    fun getAllFavorites():MutableLiveData<List<String>>{
        return  favoriteProvider.getFavoriteItems()
    }

    fun getProductsFromIds(listOfIds : List<String>): MutableLiveData<List<Products>> {
        return productsDataSource.getProductsFromIds(listOfIds)
    }

}
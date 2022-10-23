package com.tech4decv.myshop.ui.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.data.repository.FavoriteRepository

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val favoriteRepository = FavoriteRepository(application)

    fun isFavorite(productId : String): Boolean{
        return favoriteRepository.isFavorite(productId)
    }

    fun removeFromFavorite(id: String) {
        favoriteRepository.removeProduct(id)

    }

    fun addToFavorite(id: String) {
        favoriteRepository.addProduct(id)

    }

    fun getAllFavoriteProducts(): MutableLiveData<List<String>>{
        return  favoriteRepository.getAllFavorites()
    }

    fun getProductFromId(listOfString : List<String>): MutableLiveData<List<Products>>{
        return favoriteRepository.getProductsFromIds(listOfString)
    }

}
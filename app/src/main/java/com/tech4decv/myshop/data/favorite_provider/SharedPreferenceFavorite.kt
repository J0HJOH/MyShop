package com.tech4decv.myshop.data.favorite_provider

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

class SharedPreferenceFavorite(context: Context): FavoriteProvider {
    //Created the sharedPreference file
    private val favoriteStorage : SharedPreferences = context
        .getSharedPreferences("FAVORITE",Context.MODE_PRIVATE)

    //Create an editor instance, it updates the shared Preference data.
    private  val editor : SharedPreferences.Editor = favoriteStorage.edit()

    //create an instance of live data
    private val liveData = MutableLiveData<List<String>>(listOf())


    override fun addFavorite(productId: String) {
        editor.putString(productId,productId)
        editor.commit()

        notifyObservers()

    }

    override fun removeFavorite(productId: String) {
        editor.remove(productId)
        editor.commit()

        notifyObservers()
    }

    override fun isFavorite(productId: String): Boolean {
        val item : String? = favoriteStorage.getString(productId,"")
        if(item.isNullOrEmpty()){
            return false
        }
        return true
    }

    override fun getFavoriteItems(): MutableLiveData<List<String>> {
        notifyObservers()
        return liveData
    }

    private fun notifyObservers(){
        liveData.value = favoriteStorage.all.keys.toList()

    }
}
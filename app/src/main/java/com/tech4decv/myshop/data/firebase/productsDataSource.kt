package com.tech4decv.myshop.data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tech4decv.myshop.data.models.Products

class ProductsDataSource {
//    get reference to firestore database
    private val dp = Firebase.firestore
//This function fetches the product from firebase
    fun getProductInfo(): MutableLiveData<List<Products>>{
        val productLiveData = MutableLiveData<List<Products>>()
        dp.collection("products")
            .get()
                //This addOnSuccessListener is a callBack function, when my order to fetch data is ready
            .addOnSuccessListener { documents ->
                val listOfProducts : List<Products> = documents.toObjects(Products::class.java)
                productLiveData.value = listOfProducts

            }
            .addOnFailureListener{ error ->
                Log.e("Firebase Error", error.message.toString())
            }
        return productLiveData
    }
}
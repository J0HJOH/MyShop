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
        val productLiveData = MutableLiveData<List<Products>>(listOf())
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
//This takes a list of ids, and returns liveData of products which can be observed
    fun getProductsFromIds(productIds: List<String>): MutableLiveData<List<Products>>{
        val productLiveData = MutableLiveData<List<Products>>(listOf())

        if(productIds.isEmpty())return productLiveData

        dp.collection("products")
                .whereIn("id", productIds)
                .get()
                .addOnSuccessListener { documents->
                    val listOfProducts : List<Products> = documents.toObjects(Products::class.java)
                    productLiveData.value = listOfProducts

                }
                .addOnFailureListener { error->
                    Log.e("Firebase Error", error.message.toString())
                }
        return productLiveData
    }
}
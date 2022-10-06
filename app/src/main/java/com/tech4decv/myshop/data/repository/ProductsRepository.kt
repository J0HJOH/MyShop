package com.tech4decv.myshop.data.repository

import androidx.lifecycle.MutableLiveData
import com.tech4decv.myshop.data.firebase.ProductsDataSource
import com.tech4decv.myshop.data.models.Products

class ProductsRepository {

    fun getProducts(): MutableLiveData<List<Products>>{
        val productDataSource  = ProductsDataSource()


        return  productDataSource.getProductInfo()
    }
}
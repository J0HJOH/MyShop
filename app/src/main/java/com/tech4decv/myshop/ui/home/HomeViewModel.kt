package com.tech4decv.myshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.data.repository.ProductsRepository

class HomeViewModel : ViewModel() {
    private val productsRepository = ProductsRepository()
    private val products : MutableLiveData<List<Products>> = productsRepository.getProducts()

    fun getAllProducts():  MutableLiveData<List<Products>> {
        return  products
    }
}
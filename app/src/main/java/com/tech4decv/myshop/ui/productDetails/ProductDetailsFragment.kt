package com.tech4decv.myshop.ui.productDetails

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.databinding.FragmentProductDetailsListDialogBinding

class ProductDetailsFragment(val products : Products) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        binding = FragmentProductDetailsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(requireContext())
            .load(products.image)
            .into(binding.productImage)

        //show details
        binding.productName.text = products.name
        binding.productPrice.text = "$${products.price}"
        binding.productSeller.text = products.seller
        binding.productSize.text = products.size

        binding.addToCart.setOnClickListener{
            //save to the shared preference
            val id :String = products.id ?: ""
            //save id to shared preference
            productDetailsViewModel.saveToCart(products)
            //alert user the item has been added to cart
            Toast.makeText(requireContext(),"Saved to Cart", Toast.LENGTH_LONG).show()
            binding.addToCart.text = "Remove from Cart"
            //change button color
        }

        binding.selectFavButton.setOnClickListener{
            binding.selectFavButton.setBackgroundColor(Color.RED)
        }
    }
}
package com.tech4decv.myshop.ui.productDetails

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tech4decv.myshop.R
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.databinding.FragmentProductDetailsListDialogBinding
import com.tech4decv.myshop.ui.favourite.FavouriteViewModel

class ProductDetailsFragment(val products : Products) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentProductDetailsListDialogBinding
    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private lateinit var favoriteViewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        productDetailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        favoriteViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

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

// sets listener to cart button
        binding.addToCart.setOnClickListener{
            //save to the shared preference
            val id :String = products.id ?: ""

            //save id to cart
            productDetailsViewModel.saveToCart(products)
            //alert user the item has been added to cart
            Toast.makeText(requireContext(),"Saved to Cart", Toast.LENGTH_LONG).show()

            //close bottomSheet
            this.dismiss()
        }

        //sets listener to favorite button
        binding.selectFavButton.setOnClickListener{
            toggleFavoriteButton()
        }

        //show Appropriate Icon
        if(favoriteViewModel.isFavorite(products.id!!)){
            //show as selected
            binding.selectFavButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)


        }
    }

    private fun toggleFavoriteButton() {
        /*checks if product exists in favorite data store/provider and performs a task
        *changing the color of the icon
         */
        if (favoriteViewModel.isFavorite(products.id!!)) {
            //remove item from storage
            favoriteViewModel.removeFromFavorite(products.id!!)
            //show as not selected
            binding.selectFavButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        } else {
            //add item to storage
            favoriteViewModel.addToFavorite(products.id!!)
            //show as selected
            binding.selectFavButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
    }
}
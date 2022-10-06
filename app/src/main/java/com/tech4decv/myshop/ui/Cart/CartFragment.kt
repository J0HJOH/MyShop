package com.tech4decv.myshop.ui.Cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tech4decv.myshop.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private  lateinit var cartViewModel :CartViewModel

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartViewModel =
            ViewModelProvider(this).get(CartViewModel::class.java)

        binding = FragmentCartBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                var productInCart = cartViewModel.getProducts()
        Toast.makeText(requireContext(),"There are ${productInCart.size} in cart",Toast.LENGTH_LONG)
            .show()

    }


}
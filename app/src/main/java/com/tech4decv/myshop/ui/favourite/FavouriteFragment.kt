package com.tech4decv.myshop.ui.favourite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tech4decv.myshop.databinding.FragmentFavouriteBinding
import com.tech4decv.myshop.ui.home.ProductsAdapter

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var viewModel: FavouriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            viewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        binding= FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getAllFavoriteProducts().observe(viewLifecycleOwner){ listOfIds ->
            viewModel.getProductFromId(listOfIds).observe(viewLifecycleOwner){
                if(it.isNotEmpty()){
                    binding.listOfFavorites.visibility = View.VISIBLE
                    binding.nothingHere.visibility = View.GONE
                    binding.listOfFavorites.adapter = ProductsAdapter(requireContext(),it,childFragmentManager)
                    binding.listOfFavorites.layoutManager = GridLayoutManager(requireContext(),2)
                    val dividerItemDecoration = DividerItemDecoration(requireContext(), RecyclerView.HORIZONTAL)
                    binding.listOfFavorites.addItemDecoration(dividerItemDecoration)
                }else{
                    //Favorite is empty
                    //set visibility of the textView saying it is empty
                    binding.nothingHere.visibility = View.GONE
                    binding.nothingHere.visibility = View.VISIBLE

                }
            }
        }
    }

}
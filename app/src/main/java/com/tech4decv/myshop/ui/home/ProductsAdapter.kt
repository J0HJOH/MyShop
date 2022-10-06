package com.tech4decv.myshop.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tech4decv.myshop.R
import com.tech4decv.myshop.data.models.Products
import com.tech4decv.myshop.ui.productDetails.ProductDetailsFragment

class ProductsAdapter(
    val context: Context,
    val listOfProducts:List<Products>,
    val fragmentManager: FragmentManager
    ) : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView: View = LayoutInflater.from(context).inflate(R.layout.layout_products,parent,false)
        return ProductsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        //show product name in UI
        val products = listOfProducts[position]
        holder.productName.text =  products.name
        //shows product image to Ui
        holder.productPrice.text = "$${products.price}"
        //show image
        val imageUrl = products.image
        Glide.with(context)

            .load(imageUrl)
            .into(holder.productImage)

        //seting a listener to each item
        holder.itemView.setOnClickListener{
            ProductDetailsFragment(products).show(fragmentManager,"tag")
        }


    }

    override fun getItemCount(): Int = listOfProducts.size

}
class ProductsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val productName : TextView = itemView.findViewById(R.id.product_name)
    val productPrice : TextView = itemView.findViewById(R.id.product_price)
    val productImage : ImageView = itemView.findViewById(R.id.product_image)

}
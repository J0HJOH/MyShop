package com.tech4decv.myshop.ui.Cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.tech4decv.myshop.R
import com.tech4decv.myshop.data.models.Products

class CartAdapter(val context : Context, val cartViewModel: CartViewModel ):RecyclerView.Adapter<CartViewHolder>() {
    private val listOfSelectedProducts: List<Products> = cartViewModel.getProducts()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_cart,parent,false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product : Products = listOfSelectedProducts[position]

        //show name
        holder.productName.text = product.name
        //show price
        holder.price.text = "$${product.price}"
        //show image
        Glide.with(context)
            .load(product.image)
            .into(holder.image)
        //specify Quantity
        val quantity = cartViewModel.getQuantity(product)
        holder.quantity.text = quantity.toString()

        //increase quantity
        holder.increaseBtn.setOnClickListener {
           cartViewModel.increaseQuantity(product).toString()
        }

        //decrease quantity
        holder.decreaseBtn.setOnClickListener {
            disableButton(quantity,holder.decreaseBtn)
            cartViewModel.decreaseQuantity(product).toString()
        }

        //remove product from cart
        holder.deleteBtn.setOnClickListener {
            cartViewModel.removeFromCart(product)
            Toast.makeText(context,"You removed ${product.name} from Cart", Toast.LENGTH_LONG).show()
        }

    }

    private fun disableButton(quantity: Int,button: MaterialButton){
        button.isEnabled = quantity > 1
    }



    override fun getItemCount(): Int = listOfSelectedProducts.size

}

class CartViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
    val image : ImageView = itemView.findViewById(R.id.cart_image)
    val productName : TextView = itemView.findViewById(R.id.product_name)
    val price : TextView = itemView.findViewById(R.id.price)
    val increaseBtn : MaterialButton = itemView.findViewById(R.id.increase_quantity)
    val quantity : TextView = itemView.findViewById(R.id.quantity)
    val decreaseBtn : MaterialButton = itemView.findViewById(R.id.decrease_quantity)
    val deleteBtn : MaterialButton = itemView.findViewById(R.id.delete_product  )


}

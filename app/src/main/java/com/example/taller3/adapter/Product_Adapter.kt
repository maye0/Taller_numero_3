package com.example.taller3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.example.taller3.R
import com.example.taller3.model.Product
import kotlinx.android.synthetic.main.item_product.view.*

class Product_Adapter(private val products: MutableList<Product>, private val callback: (Product, String) -> Unit): RecyclerView.Adapter<Product_Adapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View, val callback: (Product, String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val contador = 0
        fun bind(item: Product){
            itemView.nameTextView.text = item.name
            itemView.descripTextView.text = item.description
            itemView.stock_product.text = item.stock.toString()
            itemView.deleteButton.setOnClickListener {
                callback(item, "Eliminar")
            }
            itemView.editButton.setOnClickListener {
                callback(item, "Editar")
            }
            itemView.addButton.setOnClickListener {
                callback(item, "Aumentar")
            }
            itemView.no_addButton.setOnClickListener {
                callback(item, "Disminuir")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
       holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun addProduct(product: Product){
        products.add(product)
    }

    fun deleteProduct(product: Product){
        products.remove(product)
    }

    fun editProduct(product: Product, newName: String, newDesciption:String){
        val index = products.indexOf(product)
        products[index].name = newName
        product.description = newDesciption
        notifyDataSetChanged()
    }

    fun changeStock(product: Product, number: Int) {
        val index = products.indexOf(product)
        products[index].stock = number
        notifyDataSetChanged()
    }

}
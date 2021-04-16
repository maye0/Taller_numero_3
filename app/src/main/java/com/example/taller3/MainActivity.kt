package com.example.taller3

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taller3.adapter.Product_Adapter
import com.example.taller3.dialogs.ProductDialogs
import com.example.taller3.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //Lateint me permite asegurarle a Android que esta Variable sera inicializada mas adelante
    lateinit var adapter: Product_Adapter
    lateinit var products: MutableList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setuButtons()
        setupList()
    }


    private fun setuButtons() {
        Add_button.setOnClickListener {
            val dialog = ProductDialogs(context = this, "", "") { name, description ->
                //Codigo para agregar un poducto
                addProduct(name, description)
            }
            dialog.show()
        }
    }

    private fun setupList() {
        products = mutableListOf<Product>()

        adapter = Product_Adapter(products) { item, action ->
            if (action == "Eliminar") {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.title_delete))
                builder.setMessage(getString(R.string.subtitle_delete))
                builder.setPositiveButton(R.string.agree) { _, _ ->
                    this.deleteProduct(item)
                }
                builder.setNegativeButton(getString(R.string.cancel), null)
                builder.setCancelable(false)
                builder.show()
            } else if (action == "Editar") {
                this.editProductDialog(item)
            } else if (action == "Aumentar") {
                this.changeStock(item, 1)
            } else if (action == "Disminuir") {
                this.changeStock(item, -1)
            }

        }
        product_list.adapter = adapter
        product_list.layoutManager = LinearLayoutManager(this)
    }

    private fun addProduct(name: String, description: String) {
        val product = Product(name, description, stock = 0)
        adapter.addProduct(product)
        adapter.notifyDataSetChanged()
    }

    private fun deleteProduct(product: Product) {
        //Crear una confirmacion de eliminacion
        adapter.deleteProduct(product)
        adapter.notifyDataSetChanged()

    }

    private fun updateProduct(product: Product, name: String, description: String) {
        adapter.editProduct(product, name, description)
        adapter.notifyDataSetChanged()
    }

    private fun editProduct(product: Product) {
        val dialog = ProductDialogs(this, product.name, product.description) { name, description ->
            updateProduct(product, name, description)
        }
        dialog.show()
    }

    private fun editProductDialog (product: Product) {
        val dialog = ProductDialogs(this, product.name, product.description) { name, description ->
            adapter.editProduct(product, name, description)
        }
        dialog.show()
    }

    private fun changeStock(product: Product, number: Int) {
        if (product.stock+number >= 0) {
            adapter.changeStock(product, product.stock+number)
        }
    }

}


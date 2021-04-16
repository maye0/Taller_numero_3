package com.example.taller3.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.view.View
import android.widget.Toast
import com.example.taller3.R
import kotlinx.android.synthetic.main.dialog_product.*
import kotlinx.android.synthetic.main.item_product.*

class ProductDialogs(context: Context, val name:String, val description:String,private val callback: (String, String) -> Unit) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_product)

        nameEditext.setText(name)
        Description_Editext.setText(description)

         saveButton.setOnClickListener {
            val name = nameEditext.text.toString()
            val description = Description_Editext.text.toString()
            //enviarle esa informacion al mainActivity
             if (name.isNotEmpty() && description.isNotBlank()) {
                 callback(name, description)
                 dismiss()
             } else {
                 Toast.makeText(context, R.string.verification_empty_product, Toast.LENGTH_LONG).show()
             }

         }

    }
}
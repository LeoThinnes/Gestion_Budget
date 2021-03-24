package com.td3.gestionbudget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.td3.gestionbudget.classes.categorie
import com.td3.gestionbudget.handler.DatabaseHandler
import kotlinx.android.synthetic.main.activity_ajout_depenses.*
import kotlinx.android.synthetic.main.ajoutercategorie.*

class AjouterCatégorie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajoutercategorie)
        supportActionBar?.hide()

    val btnAjouter = findViewById<Button>(R.id.bouton_modifier)

        btnAjouter.setOnClickListener {
            saveRecord()
        }

    }


    fun saveRecord(){
        val label = nomCategorie.text.toString()
        val description = textDescription.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if(label.trim()!="" && description.trim()!=""){
            val status = databaseHandler.addCategorieRevenu(categorie(label, description))
            if(status > -1){
                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()
                nomCategorie.text.clear()
                textDescription.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
        }

    }


}
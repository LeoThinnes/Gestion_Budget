package com.td3.Economie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.td3.Economie.classes.categorie
import com.td3.Economie.handler.DatabaseHandler
import kotlinx.android.synthetic.main.ajoutercategorie.*

class AjouterCatégorie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajoutercategorie)
        supportActionBar?.hide()

    val btnAjouter = findViewById<Button>(R.id.bouton_Ajouter)

        btnAjouter.setOnClickListener {
            ajouterCategorie()
        }
    }


    fun ajouterCategorie(){
        val label = nomCategorie.text.toString()
        val description = textDescription.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if(label.trim()!="" && description.trim()!=""){
            val status = databaseHandler.ajouterCategorie(categorie(label, description))
            if(status > -1){
                Toast.makeText(applicationContext,"Catégorie enregistrée",Toast.LENGTH_LONG).show()
                nomCategorie.text.clear()
                textDescription.text.clear()
            }
        }else{
            Toast.makeText(applicationContext,"tous les champs doivent être remplis",Toast.LENGTH_LONG).show()
        }

    }


}
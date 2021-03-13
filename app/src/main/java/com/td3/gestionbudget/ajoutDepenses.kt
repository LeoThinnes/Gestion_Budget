package com.td3.gestionbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner

class ajoutDepenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_depenses)
        supportActionBar?.hide()

        val boutonAjouterCatégorie = findViewById<ImageButton>(R.id.boutonAjouterCategorie)
        val boutonModifierCatégorie = findViewById<ImageButton>(R.id.boutonModifier)

        var tableau = arrayOf("catégorie 1 ", "catégorie 2 ","catégorie 3 ", "catégorie 4 ","catégorie 5 ", "catégorie 6 ")

        val spinner: Spinner = findViewById(R.id.listeDeroulante)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                android.R.id.text1,
                tableau
        )

        spinner.adapter=adapter

        boutonAjouterCatégorie.setOnClickListener {
            val intent = Intent(this, AjouterCatégorie::class.java)
            startActivity(intent)
        }

        boutonModifierCatégorie.setOnClickListener {
            val intent = Intent(this, ModifierCatégorie::class.java)
            startActivity(intent)
        }
    }
}
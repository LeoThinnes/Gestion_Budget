package com.td3.Economie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Parametres : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parametres)
        supportActionBar?.hide()

        val btnReinitialiser = findViewById<Button>(R.id.btnReinitaliser)
        btnReinitialiser.setOnClickListener{
            val intent = Intent(this, ReinitaliserDonnees::class.java)
            startActivity(intent)
        }

        val btnAjouterCategorie = findViewById<Button>(R.id.btnParametresCategorie)
        btnAjouterCategorie.setOnClickListener {
            val intent = Intent(this, AjouterCatégorie::class.java)
            startActivity(intent)
        }
    }

}
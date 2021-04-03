package com.td3.Economie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.td3.Economie.handler.DatabaseHandler

class ReinitaliserDonnees : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duree_de_cycle)
        supportActionBar?.hide()

        val boutonReinitialiser = findViewById<Button>(R.id.btnRéinitialiser)
        boutonReinitialiser.setOnClickListener {
            supprimerDonnees()
        }
    }

    //fonction qui permet la réinitialisation des données
    fun supprimerDonnees(){
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        databaseHandler.supprimerRevenus()
        databaseHandler.supprimerDepenses()
        Toast.makeText(applicationContext,"Données Réinitialisées", Toast.LENGTH_LONG).show()
    }
}
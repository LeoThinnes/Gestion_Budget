package com.td3.gestionbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import com.td3.gestionbudget.classes.categorie
import com.td3.gestionbudget.handler.DatabaseHandler
import kotlinx.android.synthetic.main.activity_ajout_depenses.*

class ajouterRevenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajouter_revenu)
        supportActionBar?.hide()

        val boutonAjouterCatégorie = findViewById<ImageButton>(R.id.boutonAjouterCategorie)
        val boutonModifierCatégorie = findViewById<ImageButton>(R.id.boutonModifier)

        viewRecord()


        boutonAjouterCatégorie.setOnClickListener {
            val intent = Intent(this, AjouterCatégorie::class.java)
            startActivity(intent)
        }

        boutonModifierCatégorie.setOnClickListener {
            val intent = Intent(this, ModifierCatégorie::class.java)
            startActivity(intent)
        }

    }

    override fun onRestart() {
        super.onRestart()
        viewRecord()
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    fun viewRecord(){
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val categories: List<categorie> = databaseHandler.viewCategoriesRevenu()
        val ArrayLabel = Array<String>(categories.size){"null"}
        var index = 0
        for(e in categories){
            ArrayLabel[index] = e.label
            index++
        }
        //creating custom ArrayAdapter
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ArrayLabel)

        listeDeroulante.adapter = dataAdapter
    }
}
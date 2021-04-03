package com.td3.Economie

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.td3.Economie.classes.categorie
import com.td3.Economie.classes.depensesRevenus
import com.td3.Economie.handler.DatabaseHandler
import kotlinx.android.synthetic.main.activity_ajout_depenses.*


class ajoutDepenses : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajout_depenses)
        supportActionBar?.hide()


        val boutonAjouterCatégorie = findViewById<ImageButton>(R.id.boutonAjouterCategorie)
        val boutonAjouter = findViewById<Button>(R.id.bouton_Ajouter)
        viewRecord()

        boutonAjouterCatégorie.setOnClickListener {
            val intent = Intent(this, AjouterCatégorie::class.java)
            startActivity(intent)
        }


        boutonAjouter.setOnClickListener {
            ajouterDepense()
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
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val categories: List<categorie> = databaseHandler.afficherCategories()
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

    fun ajouterDepense(){
        //recupération des données saisies
        val montant = saisieMontant.text.toString()
        val categorie = listeDeroulante.selectedItem.toString()
        val note = zoneTexte.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //on controle que les champs nécessaires soient remplis
        if(montant.trim()!="" && categorie.trim()!="" && note.trim()!=""){
            //sauvegarde du liver
            val montantBis = montant.toDouble()
            val status = databaseHandler.ajouterDepense(depensesRevenus(montantBis,categorie, note))

            if(status > -1){
                //affichage d'un toast pour signaler que la sauvegarde est bonne
                Toast.makeText(applicationContext,"depense savegardée", Toast.LENGTH_LONG).show()
                //remise a vide des champs de saisie
                saisieMontant.text.clear()
                zoneTexte.text.clear()
            }
        }else{
            //affichage d'un toast si un champ n'est pas remplis correctement
            Toast.makeText(applicationContext,"tous les champs doivent etre remplis", Toast.LENGTH_LONG).show()
        }

    }

}
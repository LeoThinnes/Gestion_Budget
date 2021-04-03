package com.td3.Economie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.td3.Economie.classes.MyListAdapter
import com.td3.Economie.classes.depensesRevenus
import com.td3.Economie.handler.DatabaseHandler
import kotlinx.android.synthetic.main.activity_detail.*

class detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        afficherRevenu()
        afficherDepense()

        val boutonRetour = findViewById<Button>(R.id.boutonRetour)

        boutonRetour.setOnClickListener {
            finish()
        }
    }

    //fonction permettant d'afficher les revenus sous forme de liste
    fun afficherRevenu(){
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //demande les données de la base sous forme de tableau
        val revenus: List<depensesRevenus> = databaseHandler.afficherRevenus()
        val revenuArrayMontant = Array<String>(revenus.size){"0"}
        val revenuArrayCategorie = Array<String>(revenus.size){"null"}
        var index = 0
        //boucle recupérant tous les livres
        for(e in revenus){
            revenuArrayMontant[index] = e.montant.toString()
            revenuArrayCategorie[index] = e.categorie
            index++
        }
        //mise en forme via notre adapter personnalisé
        val myListAdapter = MyListAdapter(this,revenuArrayMontant,revenuArrayCategorie)
        listRevenus.adapter = myListAdapter //affichage
    }

    //fonction permettant d'afficher les dépenses sous forme de liste
    fun afficherDepense(){
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //demande les données de la base sous forme de tableau
        val depenses: List<depensesRevenus> = databaseHandler.afficherDepenses()
        val depenseArrayMontant = Array<String>(depenses.size){"0"}
        val depenseArrayCategorie = Array<String>(depenses.size){"null"}
        var index = 0
        //boucle recupérant tous les livres
        for(e in depenses){
            depenseArrayMontant[index] = e.montant.toString()
            depenseArrayCategorie[index] = e.categorie
            index++
        }
        //mise en forme via notre adapter personnalisé
        val myListAdapter = MyListAdapter(this,depenseArrayMontant,depenseArrayCategorie)
        listDepenses.adapter = myListAdapter //affichage
    }
}
package com.td3.Economie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.td3.Economie.classes.NotificationUtils
import com.td3.Economie.handler.DatabaseHandler
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*



class MainActivity : AppCompatActivity() {

    private val mNotificationTime = Calendar.getInstance().timeInMillis +  86400000 //Set after 86 400 000 seconds from the current time = one day after.
    private var mNotified = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        //recuperation des variables de layout
        val boutonRevenu = findViewById<Button>(R.id.boutonRevenus)
        val boutonDepenses = findViewById<Button>(R.id.boutonDepenses)
        val boutonParametres = findViewById<ImageButton>(R.id.BoutonParametre)
        val lienDetail: TextView = findViewById<TextView>(R.id.lienDetail)

        //actions des différents boutons
        boutonRevenu.setOnClickListener {
            val intent = Intent(this, ajouterRevenu::class.java)
            startActivity(intent) //changement d'activité
        }

        boutonDepenses.setOnClickListener {
            val intent = Intent(this, ajoutDepenses::class.java)
            startActivity(intent)   //changement d'activité
        }

        boutonParametres.setOnClickListener {
            val intent = Intent(this, Parametres::class.java)
            startActivity(intent)   //changement d'activité
        }

        lienDetail.setOnClickListener {
            val intent = Intent(this, detail::class.java)
            startActivity(intent)   //changement d'activité
        }

        //lancement des notifications
        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
        }

    }

    override fun onStart() {
        super.onStart()
        //affichage du solde
        afficherSolde()
    }

    //fonction permettant d'afficher le solde
    fun afficherSolde(){
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)

        val revenus: Double = databaseHandler.sommeRevenus()
        val depenses: Double = databaseHandler.sommeDepenses()

        val solde = revenus - depenses
        val textSolde: String = solde.toString()
            ValeurSolde.setText(textSolde + "€")

    }

}
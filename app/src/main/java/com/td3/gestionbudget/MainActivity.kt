package com.td3.gestionbudget

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.NotificationCompat
//import android.support.v7.app.AppCompatActivity
import java.util.*



class MainActivity : AppCompatActivity() {

    private val mNotificationTime = Calendar.getInstance().timeInMillis +  1000 //Set after 86 400 000 seconds from the current time = one day after.
    private var mNotified = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val boutonRevenu = findViewById<Button>(R.id.boutonRevenus)
        val boutonDepenses = findViewById<Button>(R.id.boutonDepenses)
        val boutonParametres = findViewById<ImageButton>(R.id.BoutonParametre)
        val lienDetail: TextView = findViewById<TextView>(R.id.lienDetail)

        boutonRevenu.setOnClickListener {
            val intent = Intent(this, ajouterRevenu::class.java)
            startActivity(intent)
        }

        boutonDepenses.setOnClickListener {
            val intent = Intent(this, ajoutDepenses::class.java)
            startActivity(intent)
        }

        boutonParametres.setOnClickListener {
            val intent = Intent(this, Parametres::class.java)
            startActivity(intent)
        }

        lienDetail.setOnClickListener {
            val intent = Intent(this, detail::class.java)
            startActivity(intent)
        }

        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@MainActivity)
        }

    }

}
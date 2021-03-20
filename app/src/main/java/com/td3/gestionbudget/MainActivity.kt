package com.td3.gestionbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
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
    }

}
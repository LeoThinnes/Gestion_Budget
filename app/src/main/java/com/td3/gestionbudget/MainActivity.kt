package com.td3.gestionbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val boutonRevenu = findViewById<Button>(R.id.boutonRevenus)
        val boutonParametres = findViewById<ImageButton>(R.id.BoutonParametre)

        boutonRevenu.setOnClickListener {
            val intent = Intent(this, ajouterRevenu::class.java)
            startActivity(intent)
        }

        boutonParametres.setOnClickListener {
            val intent = Intent(this, Parametres::class.java)
            startActivity(intent)
        }
    }

}
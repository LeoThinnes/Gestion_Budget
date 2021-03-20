package com.td3.gestionbudget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class detail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        val boutonRetour = findViewById<Button>(R.id.boutonRetour)

        boutonRetour.setOnClickListener {
            finish()
        }
    }
}
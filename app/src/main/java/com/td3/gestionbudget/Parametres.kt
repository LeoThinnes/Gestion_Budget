package com.td3.gestionbudget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Parametres : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parametres)

        val btnCycle = findViewById<Button>(R.id.btnCycle)
        btnCycle.setOnClickListener{
            val intent = Intent(this, DureeDeCycle::class.java)
            finish()
        }
    }

}
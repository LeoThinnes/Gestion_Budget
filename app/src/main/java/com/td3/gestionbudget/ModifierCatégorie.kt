package com.td3.gestionbudget
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.td3.gestionbudget.classes.categorie
import com.td3.gestionbudget.handler.DatabaseHandler
import kotlinx.android.synthetic.main.ajoutercategorie.*

class ModifierCatégorie : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modifiercategorie)
        supportActionBar?.hide()

    }

}
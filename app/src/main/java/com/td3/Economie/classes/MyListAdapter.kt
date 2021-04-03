package com.td3.Economie.classes

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.td3.Economie.R

class MyListAdapter(private val context: Activity, private val montant: Array<String>, private val categorie: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, categorie) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val montantText = rowView.findViewById(R.id.textViewMontant) as TextView
        val categorieText = rowView.findViewById(R.id.textViewCategorie) as TextView

        montantText.text = "Montant : ${montant[position]}"
        categorieText.text = "Catégorie : ${categorie[position]}"
        return rowView
    }
}
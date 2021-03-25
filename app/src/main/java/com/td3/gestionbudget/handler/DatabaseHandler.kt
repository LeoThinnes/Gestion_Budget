package com.td3.gestionbudget.handler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.td3.gestionbudget.classes.categorie
import com.td3.gestionbudget.classes.depensesRevenus

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "BudgetDatabase"

        private val TABLE_CATEGORIE_REVENU = "tableCategorieRevenu"
        private val KEY_LABEL = "labelCategorieRevenu"
        private val KEY_DESCRIPTION = "descriptionCategorieRevenu"

        private val TABLE_REVENUS = "tableRevenus"
        private val KEY_MONTANT_REVENUS = "montantRevenus"
        private val KEY_CATEGORIE_REVENUS = "categorieRevenus"
        private val KEY_NOTE_REVENUS = "noteRevenus"

        private val TABLE_DEPENSES = "tableDepenses"
        private val KEY_MONTANT_DEPENSES = "montantDepenses"
        private val KEY_CATEGORIE_DEPENSES = "categorieDepenses"
        private val KEY_NOTE_DEPENSES= "noteDepenses"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CATEGORIE_REVENU_TABLE = ("CREATE TABLE " + TABLE_CATEGORIE_REVENU + "(" + KEY_LABEL + " TEXT,"+ KEY_DESCRIPTION + " TEXT" + ")")
        val CREATE_REVENUS_TABLE = ("CREATE TABLE " + TABLE_REVENUS + "(" + KEY_MONTANT_REVENUS + " REAL,"+ KEY_CATEGORIE_REVENUS + " TEXT," +KEY_NOTE_REVENUS+ " TEXT" + ")")
        val CREATE_DEPENSES_TABLE = ("CREATE TABLE " + TABLE_DEPENSES + "(" + KEY_MONTANT_DEPENSES + " REAL,"+ KEY_CATEGORIE_DEPENSES + " TEXT," +KEY_NOTE_DEPENSES +" TEXT" + ")")

        db?.execSQL(CREATE_CATEGORIE_REVENU_TABLE)
        db?.execSQL(CREATE_REVENUS_TABLE)
        db?.execSQL(CREATE_DEPENSES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIE_REVENU)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_REVENUS)
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPENSES)

        onCreate(db)
    }


    //method to insert data
    fun ajouterCategorie(categorie: categorie): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_LABEL, categorie.label) // EmpModelClass Name
        contentValues.put(KEY_DESCRIPTION, categorie.description) // EmpModelClass Phone
        // Inserting Row
        val success = db.insert(TABLE_CATEGORIE_REVENU, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    //method to read data
    fun afficherCategories(): List<categorie> {
        val categorieList: ArrayList<categorie> = ArrayList<categorie>()
        val selectQuery = "SELECT  * FROM $TABLE_CATEGORIE_REVENU"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var label: String
        var description: String
        if (cursor.moveToFirst()) {
            do {
                label = cursor.getString(cursor.getColumnIndex("labelCategorieRevenu"))
                description = cursor.getString(cursor.getColumnIndex("descriptionCategorieRevenu"))
                val categorie = categorie(label = label, description = description)
                categorieList.add(categorie)
            } while (cursor.moveToNext())
        }
        return categorieList
    }


    fun ajouterRevenu(depensesRevenus: depensesRevenus):Long{
        //connexion a la bd
        val db = this.writableDatabase
        //recuperation des valeur
        val contentValues = ContentValues()
        contentValues.put(KEY_MONTANT_REVENUS, depensesRevenus.montant)
        contentValues.put(KEY_CATEGORIE_REVENUS, depensesRevenus.categorie)
        contentValues.put(KEY_NOTE_REVENUS,depensesRevenus.note )
        // Insertion des lignes
        val success = db.insert(TABLE_REVENUS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun afficherRevenus():List<depensesRevenus>{
        //creation d'un tableau de livres
        val listeRevenus:ArrayList<depensesRevenus> = ArrayList<depensesRevenus>()
        val selectQuery = "SELECT  * FROM $TABLE_REVENUS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var montant: Double
        var categorie: String
        var note: String
        if (cursor.moveToFirst()) {
            do {
                montant = cursor.getDouble(cursor.getColumnIndex("montantRevenus"))
                categorie = cursor.getString(cursor.getColumnIndex("categorieRevenus"))
                note = cursor.getString(cursor.getColumnIndex("noteRevenus"))
                val revenus= depensesRevenus(montant = montant, categorie = categorie, note = note)
                listeRevenus.add(revenus)
            } while (cursor.moveToNext())
        }
        return listeRevenus
    }

    fun ajouterDepense(depensesRevenus: depensesRevenus):Long{
        //connexion a la bd
        val db = this.writableDatabase
        //recuperation des valeur
        val contentValues = ContentValues()
        contentValues.put(KEY_MONTANT_DEPENSES, depensesRevenus.montant)
        contentValues.put(KEY_CATEGORIE_DEPENSES, depensesRevenus.categorie)
        contentValues.put(KEY_NOTE_DEPENSES,depensesRevenus.note )
        // Insertion des lignes
        val success = db.insert(TABLE_DEPENSES, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

    fun afficherDepenses():List<depensesRevenus>{
        //creation d'un tableau de livres
        val listeDepenses:ArrayList<depensesRevenus> = ArrayList<depensesRevenus>()
        val selectQuery = "SELECT  * FROM $TABLE_DEPENSES"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var montant: Double
        var categorie: String
        var note: String
        if (cursor.moveToFirst()) {
            do {
                montant = cursor.getDouble(cursor.getColumnIndex("montantDepenses"))
                categorie = cursor.getString(cursor.getColumnIndex("categorieDepenses"))
                note = cursor.getString(cursor.getColumnIndex("noteDepenses"))
                val revenus= depensesRevenus(montant = montant, categorie = categorie, note = note)
                listeDepenses.add(revenus)
            } while (cursor.moveToNext())
        }
        return listeDepenses
    }
}
package com.td3.gestionbudget.handler

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException
import com.td3.gestionbudget.classes.categorie

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "BudgetDatabase"
        private val TABLE_CATEGORIE_REVENU = "tableCategorieRevenu"
        private val KEY_LABEL = "labelCategorieRevenu"
        private val KEY_DESCRIPTION = "descriptionCategorieRevenu"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CATEGORIE_REVENU_TABLE = ("CREATE TABLE " + TABLE_CATEGORIE_REVENU + "(" + KEY_LABEL + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")")
        db?.execSQL(CREATE_CATEGORIE_REVENU_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIE_REVENU)
        onCreate(db)
    }


    //method to insert data
    fun addCategorieRevenu(categorie: categorie): Long {
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
    fun viewCategoriesRevenu(): List<categorie> {
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

    //method to update data
    fun updateCategorieRevenu(categorie: categorie): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_LABEL, categorie.label)
        contentValues.put(KEY_DESCRIPTION, categorie.description)

        // Updating Row
        val success = db.update(TABLE_CATEGORIE_REVENU, contentValues, "label=" + categorie.label, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}
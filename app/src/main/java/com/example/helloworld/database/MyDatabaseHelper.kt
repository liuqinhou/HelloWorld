package com.example.helloworld.database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, cursor: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, name, cursor, version) {
   private val createBook = "create table book (" +
           "id integer primary key autoincrement," +
           "author text," +
           "price real," +
           "pages integer," +
           "name text)"

    private val createCategory = "create table Category (" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase?) {
        //TODO("Not yet implemented")
        db?.execSQL(createBook)
        db?.execSQL(createCategory)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //TODO("Not yet implemented")
        db?.execSQL("drop table if exists book")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }
}
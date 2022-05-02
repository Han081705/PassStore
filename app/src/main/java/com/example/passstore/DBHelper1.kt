package com.example.passstore

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper1 : SQLiteOpenHelper{
    companion object{
        val DBNAME = "login.db"
    }
    constructor(context : Context) : super(context, "login.db", null, 1)

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table users(username TEXT primary key, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists users")
    }

    fun insertData(username : String, password : String): Boolean{
        var db : SQLiteDatabase = this.writableDatabase
        var values : ContentValues = ContentValues()

        values.put("username", username)
        values.put("password", password)

        var result : Long = db.insert("users", null, values)
        return !result.equals(-1)
    }

    fun updateData(username : String, password : String): Boolean{
        var db : SQLiteDatabase = this.writableDatabase
        var values : ContentValues = ContentValues()

        values.put("password", password)

        var cursor : Cursor = db.rawQuery("Select * from users where username=?", arrayOf(username))
        if(cursor.count > 0)
        {
            var result : Long = db.update("users", null,"username=?", arrayOf(username)).toLong()
            return !result.equals(-1)
        }
        else{
            return false
        }
    }

    fun deleteData(username : String, password : String): Boolean{
        var db : SQLiteDatabase = this.writableDatabase

        var cursor : Cursor = db.rawQuery("Select * from users where username=?", arrayOf(username))
        if(cursor.count > 0)
        {
            var result : Long = db.delete("users", "username=?", arrayOf(username)).toLong()
            return !result.equals(-1)
        }
        else{
            return false
        }
    }

    fun getData(): Cursor {
        var db: SQLiteDatabase = this.writableDatabase

        var cursor: Cursor = db.rawQuery("Select * from users where username=?", null)
        return cursor
    }
}
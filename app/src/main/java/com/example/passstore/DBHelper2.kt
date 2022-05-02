package com.example.passstore

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper2 : SQLiteOpenHelper{
    companion object{
        val DBNAME = "password.db"
    }
    constructor(context : Context) : super(context, "password.db", null, 1)

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table password(app TEXT primary key, icon TEXT, appUser TEXT, appPassword TEXT, appEmail TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists password")
    }

    fun insertData(app : String, icon : String, appUser : String, appPassword : String, appEmail : String): Boolean{
        var db : SQLiteDatabase = this.writableDatabase
        var values : ContentValues = ContentValues()

        values.put("app", app)
        values.put("icon", icon)
        values.put("appUser", appUser)
        values.put("appPasswoird", appPassword)
        values.put("appEmail", appEmail)

        var result : Long = db.insert("password", null, values)
        return !result.equals(-1)
    }

    fun updateData(app : String, icon : String, appUser : String, appPassword : String, appEmail : String): Boolean{
        var db : SQLiteDatabase = this.writableDatabase
        var values : ContentValues = ContentValues()

        values.put("icon", icon)
        values.put("appUser", appUser)
        values.put("appPasswoird", appPassword)
        values.put("appEmail", appEmail)

        var cursor : Cursor = db.rawQuery("Select * from users where username=?", arrayOf(app))
        if(cursor.count > 0)
        {
            var result : Long = db.update("password", null,"app=?", arrayOf(app)).toLong()
            return !result.equals(-1)
        }
        else{
            return false
        }
    }

    fun deleteData(app : String, icon : String, appUser : String, appPassword : String, appEmail : String): Boolean{
        var db : SQLiteDatabase = this.writableDatabase

        var cursor : Cursor = db.rawQuery("Select * from password where app=?", arrayOf(app))
        if(cursor.count > 0)
        {
            var result : Long = db.delete("password", "app=?", arrayOf(app)).toLong()
            return !result.equals(-1)
        }
        else{
            return false
        }
    }

    fun getData(): Cursor {
        var db: SQLiteDatabase = this.writableDatabase

        var cursor: Cursor = db.rawQuery("Select * from password where app=?", null)
        return cursor
    }
}
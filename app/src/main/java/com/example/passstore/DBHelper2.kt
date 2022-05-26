package com.example.passstore

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper2(context: Context?) :
    SQLiteOpenHelper(context, "Users.db", null, 1) {
    override fun onCreate(DB: SQLiteDatabase) {
        DB.execSQL("create Table Userdetailsdata(personid TEXT, appName TEXT, icon TEXT, appUser TEXT, appPass TEXT, appEmail TEXT)")
    }

    override fun onUpgrade(DB: SQLiteDatabase, i: Int, ii: Int) {
        DB.execSQL("drop Table if exists Userdetailsdata")
    }

    fun insertUserDetails(personid : String?, appName: String?, icon: String?, appUser: String?, appPass: String?, appEmail: String?): Boolean {
        val DB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("personid", personid)
        contentValues.put("appName", appName)
        contentValues.put("icon", icon)
        contentValues.put("appUser", appUser)
        contentValues.put("appPass", appPass)
        contentValues.put("appEmail", appEmail)
        val result = DB.insert("Userdetailsdata", null, contentValues)
        return result != -1L
    }

    fun updateUserDetails(personid : String?, appName: String?, icon: String?, appUser: String?, appPass: String?, appEmail: String?): Boolean {
        val DB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("appName", appName)
        contentValues.put("icon", icon)
        contentValues.put("appUser", appUser)
        contentValues.put("appPass", appPass)
        contentValues.put("appEmail", appEmail)
        val cursor = DB.rawQuery("Select * from Userdetailsdata where personid = ?", arrayOf(personid))
        if (cursor.count > 0) {
            val result = DB.update("Userdetailsdata", contentValues, "personid=?", arrayOf(personid)).toLong()
            return result != -1L
        } else {
            return false
        }
    }

    fun deleteUserDetails(personid: String?, appName: String?, appUser: String?, appPass: String?, appEmail: String?): Boolean {
        val DB = this.writableDatabase
        val cursor = DB.rawQuery("Select * from Userdetailsdata where personid = '${personid}'", arrayOf(personid))
        if (cursor.count > 0) {
            val result = DB.delete("Userdetailsdata", "personid = '${personid}', appName = '${appName}', appUser = '${appUser}', appPass = '${appPass}', appEmail = '${appEmail}'", arrayOf(personid)).toLong()
            return result != -1L
        } else {
            return false
        }
    }

    fun getUserDetailsData(personid : String?): Cursor {
        val DB = this.writableDatabase
        return DB.rawQuery("Select * from Userdetailsdata where personid='${personid}'", null)
    }
}
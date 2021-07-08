package com.example.notes.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreference {
    private const val INIT_USER = "initUser"
    private const val DATABASE_TYPE = "dbType"
    private const val PREF_NAME = "preference"

    private lateinit var preferences: SharedPreferences

    fun getPreference(context: Context):SharedPreferences{
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences
    }

    fun setInitUser(init: Boolean){
        preferences.edit().putBoolean(INIT_USER, init).apply()
    }

    fun setDbType(type: String){
        preferences.edit().putString(DATABASE_TYPE, type).apply()
    }

    fun getInitUser() = preferences.getBoolean(INIT_USER, false)

    fun getDbType() = preferences.getString(DATABASE_TYPE, TYPE_ROOM).toString()

}
package com.example.realestateagency1.ui.util

import android.content.Context
import android.content.SharedPreferences

class Pref ( context: Context) {
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("presences", Context.MODE_PRIVATE)

    fun isBoardingShowed(): Boolean {
        return sharedPref.getBoolean("board", false)
    }

    fun setBoardingShowed(isSnow: Boolean) {
        sharedPref.edit().putBoolean("board", isSnow).apply()
    }
}
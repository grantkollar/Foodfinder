package app.foodfinderapp.util

import android.content.Context
import android.content.SharedPreferences
import app.foodfinderapp.Application.Companion.context

object ConfigData {

    var isNight = false

    var isNightName = "isNight"

    var SharedDataName = "configData"

    fun getIsNight(): Boolean {
        val preferences: SharedPreferences = context
            .getSharedPreferences(ConfigData.SharedDataName, Context.MODE_PRIVATE)
        ConfigData.isNight = preferences.getBoolean(ConfigData.isNightName, false)
        return ConfigData.isNight
    }

    fun setIsNight(isNight: Boolean) {
        val editor: SharedPreferences.Editor = context
            .getSharedPreferences(ConfigData.SharedDataName, Context.MODE_PRIVATE).edit()
        editor.putBoolean(ConfigData.isNightName, isNight)
        editor.apply()
    }

}
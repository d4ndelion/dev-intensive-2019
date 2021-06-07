package ru.skillbranch.devintensive.ui.profile

import android.content.SharedPreferences
import android.preference.PreferenceManager
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val FIRST_NAME = "FIRST_NAME"
    private const val SECOND_NAME = "SECOND_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"

    private val prefs:SharedPreferences by lazy{
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfile(): Profile = Profile(
        prefs.getString(FIRST_NAME, "")!!,
        prefs.getString(SECOND_NAME, "")!!,
        prefs.getString(ABOUT, "")!!,
        prefs.getString(REPOSITORY, "")!!,
        prefs.getInt(RATING, 0),
        prefs.getInt(RESPECT, 0),
    )

    fun saveProfile(profile: Profile) {
        with(profile){
            putValue(FIRST_NAME to firstName)
            putValue(SECOND_NAME to secondName)
            putValue(ABOUT to description)
            putValue(REPOSITORY to repository)
            putValue(RATING to rating)
            putValue(RESPECT to respect)
        }
    }

    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()) {
        val key = pair.first
        when(val value = pair.second){
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("only primitive types are allowed!")
        }
        apply()
    }
}
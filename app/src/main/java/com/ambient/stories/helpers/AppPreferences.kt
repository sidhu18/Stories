package com.ambient.stories.helpers

import android.content.Context
import android.content.SharedPreferences

object AppPreferences{

    private const val NAME = "StoriesApp"
    private const val MODE = Context.MODE_PRIVATE

    private const val USER_ID = "USER_ID"

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var loggedInUserId : Long
        get() = preferences.getLong(USER_ID,-1)
        set(value) = preferences.edit {
            it.putLong(USER_ID, value)
        }
}
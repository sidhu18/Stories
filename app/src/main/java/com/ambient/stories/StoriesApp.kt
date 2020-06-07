package com.ambient.stories

import android.app.Application
import com.ambient.stories.helpers.AppPreferences

class StoriesApp : Application(){

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}
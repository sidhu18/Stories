package com.ambient.stories.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ambient.stories.data.dao.UserDao
import com.ambient.stories.data.entities.UserData

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class StoriesDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {

        @Volatile
        private var INSTANCE: StoriesDatabase? = null

        fun getInstance(context: Context): StoriesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StoriesDatabase::class.java,
                        "stories_app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
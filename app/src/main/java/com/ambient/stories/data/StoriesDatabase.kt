package com.ambient.stories.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ambient.stories.data.dao.PostDao
import com.ambient.stories.data.dao.UserDao
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.UserData

@Database(entities = [UserData::class,PostData::class], version = 4, exportSchema = false)
abstract class StoriesDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val postDao : PostDao

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
//                        .addCallback(callback)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private val callback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                    INSTANCE?.userDao?.insertUser(
                        UserData(
                            userId = 1,
                            userName = "Ash",
                            userBio = "Pokemon Trainer",
                            postCount = 3,
                            following_count = 234,
                            followersCount = 1245,
                            profileImageUri = "https://i2.wp.com/nintendosoup.com/wp-content/uploads/2020/02/ash-pikachu-feb132020.jpg"
                        )
                    )
            }
        }
    }
}
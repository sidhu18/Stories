package com.ambient.stories.data
import com.ambient.stories.data.dao.UserDao
import kotlin.jvm.Throws
import org.junit.Before
import org.junit.runner.RunWith
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ambient.stories.data.entities.UserData
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Test
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class StoriesDatabaseTest {

    private lateinit var userDao: UserDao
    private lateinit var db: StoriesDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, StoriesDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        userDao = db.userDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val user = UserData(
            userId = 1,
            userName = "Ash",
            userBio = "Pokemon Trainer",
            postCount = 3,
            following_count = 234,
            followersCount = 1245,
            profileImageUri = "https://i2.wp.com/nintendosoup.com/wp-content/uploads/2020/02/ash-pikachu-feb132020.jpg"
        )
        userDao.insertUser(user)
        val userFetch = userDao.getUser(1)
        assertEquals(userFetch, user)
    }
}
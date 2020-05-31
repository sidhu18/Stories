package com.ambient.stories.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ambient.stories.data.entities.UserData

@Dao
interface UserDao {

    @Insert
    fun insertUser(user : UserData)

    @Update
    fun updateUser(user: UserData)

    @Query ("SELECT * from user_table WHERE user_id = :key")
    fun getUser(key: Long) : UserData

//    @Delete
//    fun deleteUser(key: Long)

    @Query("DELETE FROM user_table")
    fun removeAllUsers()


}
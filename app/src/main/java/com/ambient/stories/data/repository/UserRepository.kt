package com.ambient.stories.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ambient.stories.data.dao.UserDao
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.UserData

class UserRepository(private val userDao: UserDao){

//    val userData : LiveData<UserData> = userDao.getUser(key)

    fun insertUser(user: UserData){
        userDao.insertUser(user)
    }

    fun updateUser(user: UserData){
        userDao.updateUser(user)
    }

    fun getUser(key: Long) : UserData {
        return userDao.getUser(key)
    }
}
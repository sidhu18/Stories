package com.ambient.stories.data.repository

import com.ambient.stories.data.StoriesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val database: StoriesDatabase){

    suspend fun refreshUsers(){
        withContext(Dispatchers.IO){
//            val userList =
        }
    }
}
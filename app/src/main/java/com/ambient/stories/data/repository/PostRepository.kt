package com.ambient.stories.data.repository

import androidx.lifecycle.LiveData
import com.ambient.stories.data.dao.PostDao
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.UserData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository(private var postDao: PostDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO){

    val allPosts : LiveData<List<PostData>> = postDao.getAllPosts()

    fun insertPost(postData: PostData){
        postDao.insertPost(postData)
    }

    fun updatePost(postData: PostData){
        postDao.updatePost(postData)
    }

    fun getPost(key: Long) : PostData {
        return postDao.getPost(key)
    }

    suspend fun getAllPostsT() : LiveData<List<PostData>> = withContext(ioDispatcher) {
        return@withContext postDao.getAllPosts()
    }
}
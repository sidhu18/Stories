package com.ambient.stories.data.repository

import androidx.lifecycle.LiveData
import com.ambient.stories.data.dao.PostDao
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.UserData

class PostRepository(private var postDao: PostDao){

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
}
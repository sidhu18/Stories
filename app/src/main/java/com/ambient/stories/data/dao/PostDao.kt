package com.ambient.stories.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.UserData

@Dao
interface PostDao {
    @Insert
    fun insertPost(postData: PostData)

    @Update
    fun updatePost(postData: PostData)

    @Query("SELECT * from post_table WHERE post_id = :key")
    fun getPost(key: Long) : PostData

    @Query("SELECT * from post_table ORDER BY post_id DESC")
    fun getAllPosts() : LiveData<List<PostData>>
}
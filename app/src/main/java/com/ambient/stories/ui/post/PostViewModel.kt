package com.ambient.stories.ui.post

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ambient.stories.data.StoriesDatabase
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.UserData
import com.ambient.stories.data.repository.PostRepository
import com.ambient.stories.data.repository.UserRepository
import kotlinx.coroutines.*

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val postRepository : PostRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        val postDao = StoriesDatabase.getInstance(application).postDao
        postRepository = PostRepository(postDao)
    }

    fun addPost(){
        insertPost(
            PostData(0,1,"Sample2","Test post2","",0,"06 June 2020")
        )
    }

    private fun insertPost(postData : PostData){
        uiScope.launch {
            insert(postData)
        }
    }

    private suspend fun insert(postData: PostData){
        withContext(Dispatchers.IO) {postRepository.insertPost(postData)}
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
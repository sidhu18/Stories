package com.ambient.stories.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.ambient.stories.data.StoriesDatabase
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.entities.PostWithUserData
import com.ambient.stories.data.repository.PostRepository
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val postRepository : PostRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

//    var allPosts : LiveData<List<PostData>>
//        get() = _allPosts


    private val _allPosts = MutableLiveData<List<PostWithUserData>>()

    val allPost: LiveData<List<PostWithUserData>> = _allPosts

    init {
        val postDao = StoriesDatabase.getInstance(application).postDao
        postRepository = PostRepository(postDao)

        getAllPost()

    }

    private fun getAllPost() {
        uiScope.launch {
            _allPosts.value = getPosts()
        }
    }

    private suspend fun getPosts(): List<PostWithUserData>? {
        return withContext(Dispatchers.IO){ postRepository.getAllPostsWithUserData() }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
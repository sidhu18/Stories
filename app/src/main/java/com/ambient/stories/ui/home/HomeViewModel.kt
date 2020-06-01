package com.ambient.stories.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ambient.stories.data.StoriesDatabase
import com.ambient.stories.data.entities.PostData
import com.ambient.stories.data.repository.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val postRepository : PostRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var allPosts : LiveData<List<PostData>>

    init {
        val postDao = StoriesDatabase.getInstance(application).postDao
        postRepository = PostRepository(postDao)

        allPosts = postRepository.allPosts

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
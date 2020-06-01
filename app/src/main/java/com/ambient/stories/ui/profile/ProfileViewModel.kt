package com.ambient.stories.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.ambient.stories.data.StoriesDatabase
import com.ambient.stories.data.entities.UserData
import com.ambient.stories.data.repository.UserRepository
import kotlinx.coroutines.*

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository : UserRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var user = MutableLiveData<UserData?>()

    init {
        val userDao = StoriesDatabase.getInstance(application).userDao
        userRepository = UserRepository(userDao)
        getUser(1)
    }


    fun insert(user: UserData) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insertUser(user)
    }

    fun getUser(key : Long){
        uiScope.launch {
            user.value = getUserFromDb(key)
        }
    }

    suspend fun getUserFromDb(key: Long) : UserData? {
        return withContext(Dispatchers.IO) { userRepository.getUser(key)}
    }



    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
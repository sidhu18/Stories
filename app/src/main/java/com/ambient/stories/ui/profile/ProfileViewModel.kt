package com.ambient.stories.ui.profile

import android.app.Application
import androidx.lifecycle.*
import com.ambient.stories.data.StoriesDatabase
import com.ambient.stories.data.entities.UserData
import com.ambient.stories.data.repository.UserRepository
import com.ambient.stories.helpers.AppPreferences
import kotlinx.coroutines.*

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository : UserRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _user = MutableLiveData<UserData>()
    val user : LiveData<UserData> = _user

    init {
        val userDao = StoriesDatabase.getInstance(application).userDao
        userRepository = UserRepository(userDao)
        getUser(AppPreferences.loggedInUserId)
    }


    fun insert(user: UserData) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insertUser(user)
    }

    fun getUser(key : Long){
        uiScope.launch {
            _user.value = getUserFromDb(key)
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
package com.ambient.stories.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ambient.stories.data.StoriesDatabase
import com.ambient.stories.data.entities.UserData
import com.ambient.stories.data.repository.UserRepository
import com.ambient.stories.helpers.AppPreferences
import kotlinx.coroutines.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository : UserRepository
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED  ,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()

    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        val userDao = StoriesDatabase.getInstance(application).userDao
        userRepository = UserRepository(userDao)
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate() {
        // mock data
        AppPreferences.loggedInUserId = 1
        insert(UserData(1,
            "Harry Potter",
            "Gryffindor - Hogwarts",
            0,
            20,
            34,
            "https://i.pinimg.com/originals/9b/d2/14/9bd21442b8839d61fd3892e42bf129b2.jpg"))
    }

    private fun insert(user: UserData) = viewModelScope.launch(Dispatchers.IO) {
        val operation = async(Dispatchers.IO) {
            userRepository.insertUser(user)
        }
        operation.await()

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
package com.example.flexappusingmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flexappusingmvvm.model.Repository.UserAuthRepository
import com.example.flexappusingmvvm.model.Repository.UserDataRepository

class UserDataViewModelFactory (private val userDataRepository: UserDataRepository, private val userAuthRepository: UserAuthRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserDataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserDataViewModel(userDataRepository, userAuthRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
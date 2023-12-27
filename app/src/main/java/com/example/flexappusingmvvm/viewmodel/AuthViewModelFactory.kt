package com.example.flexappusingmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flexappusingmvvm.model.Repository.UserAuthRepository

class AuthViewModelFactory (private val userAuthRepository: UserAuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(userAuthRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
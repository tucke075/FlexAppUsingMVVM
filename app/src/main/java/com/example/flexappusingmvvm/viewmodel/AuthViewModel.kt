package com.example.flexappusingmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flexappusingmvvm.model.Repository.UserRepository

class AuthViewModel(private val userRepository: UserRepository) : ViewModel() {
    //uses live data to check status
    private val _signInStatus = MutableLiveData<Boolean>()

    val signInStatus : LiveData<Boolean> = _signInStatus

    fun login(email: String, password: String){
        userRepository.loginUser(email,password).addOnCompleteListener{ task ->
            if(task.isSuccessful)
                _signInStatus.value = task.isSuccessful
        }
    }
}
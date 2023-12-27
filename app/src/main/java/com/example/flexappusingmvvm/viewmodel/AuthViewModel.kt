package com.example.flexappusingmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flexappusingmvvm.model.Repository.UserAuthRepository

class AuthViewModel(private val userAuthRepository: UserAuthRepository) : ViewModel() {
    //uses live data to check status
    private val _signInStatus = MutableLiveData<Boolean>()
    val signInStatus : LiveData<Boolean> = _signInStatus

    fun login(email: String, password: String){
        userAuthRepository.loginUser(email,password).addOnCompleteListener{ task ->
            if(task.isSuccessful)
                _signInStatus.value = task.isSuccessful
        }
    }
}
package com.example.flexappusingmvvm.viewmodel


import androidx.lifecycle.ViewModel
import com.example.flexappusingmvvm.model.Data.UserData

import com.example.flexappusingmvvm.model.Repository.UserDataRepository
class UserDataViewModel (private val userDataRepository: UserDataRepository) : ViewModel() {
    fun saveDays(user: UserData){
        userDataRepository.saveDaysData(user)
    }
}
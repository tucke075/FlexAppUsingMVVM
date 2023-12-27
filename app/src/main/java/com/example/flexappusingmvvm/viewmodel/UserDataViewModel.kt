package com.example.flexappusingmvvm.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flexappusingmvvm.model.Data.UserData
import com.example.flexappusingmvvm.model.Repository.UserAuthRepository

import com.example.flexappusingmvvm.model.Repository.UserDataRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.auth.User
import kotlin.math.sign

class UserDataViewModel (private val userDataRepository: UserDataRepository, private val userAuthRepository: UserAuthRepository) : ViewModel() {
    private lateinit var signupAuthResult : Task<AuthResult>
    private var userData : UserData = UserData()
    val signupSuccess = MutableLiveData<Boolean>()
    fun saveData(email: String, password: String, repassword: String, name: String, titleShift: Int): Boolean {
        //call utility function to check if fields are entered correctly
        val result : Boolean = emptyCheck(email,password,repassword,name, titleShift)
        if(result) {
            signupAuthResult = userAuthRepository.registerUser(email, password).addOnCompleteListener{ task ->
                if(task.isSuccessful) {
                    userData.email = email
                    userData.name = name
                    if (titleShift == 1) { //days rn
                        userData.shift = "Registered Nurse"
                        userData.title = "Days"
                        userDataRepository.saveDaysUser(userData)
                    } else if (titleShift == 2) {
                        userData.shift = "Registered Nurse"
                        userData.title = "Nights"
                        userDataRepository.saveNightsUser(userData)
                    }else if (titleShift == 3){
                        userData.shift = "Nursing Assistant"
                        userData.title = "Days"
                        userDataRepository.saveDaysUser(userData)
                    }else if(titleShift == 4){
                        userData.shift = "Nursing Assistant"
                        userData.title = "Nights"
                        userDataRepository.saveNightsUser(userData)
                    }
                    signupSuccess.value = true
                }
            }
            return true
        }
        return false
    }
    fun checkTitle(days:Boolean, nights: Boolean, rn: Boolean, na : Boolean): Int{
        /*
        Number corresponds to type of combination value, -1 being invalid combination
         */
        return if(days && rn)
            1
        else if(days && na)
            2
        else if(nights && rn)
            3
        else if(nights && na)
            4
        else
            -1
    }
    //utility function to check if the values are empty that returns true or false
    private fun emptyCheck(email: String, password: String, repassword: String, name: String, titleShift: Int) : Boolean{
        val passwordSize = password.length
        return if(repassword != password){
            false
        }else if(passwordSize < 8){
            false
        }else if(titleShift == -1) {
            false
        }else !(email.isEmpty() || password.isEmpty() || repassword.isEmpty() || name.isEmpty())
    }

}
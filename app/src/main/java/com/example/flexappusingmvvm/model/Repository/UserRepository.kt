package com.example.flexappusingmvvm.model.Repository


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class UserRepository (private val auth: FirebaseAuth) {
    //Repository class that acts as intermediary between ViewModel and FirebaseAuth
    fun registerUser(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email,password)
    }
    fun loginUser(email: String, password: String): Task<AuthResult>{
        return auth.signInWithEmailAndPassword(email,password)
    }
    fun logoutUser(){
        auth.signOut()
    }
}
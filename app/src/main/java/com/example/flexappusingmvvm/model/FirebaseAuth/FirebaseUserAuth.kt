package com.example.flexappusingmvvm.model.FirebaseAuth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
class FirebaseUserAuth {
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()
    //all functons relating to auth
    fun dbSignup(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email,password)
    }
    fun dbSignout(){
        return auth.signOut()
    }
    fun dbSignin(email: String, password: String): Task<AuthResult>{
        return auth.signInWithEmailAndPassword(email,password)
    }
}
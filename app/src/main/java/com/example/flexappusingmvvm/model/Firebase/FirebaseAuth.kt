package com.example.flexappusingmvvm.model.Firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
class FirebaseAuth {
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
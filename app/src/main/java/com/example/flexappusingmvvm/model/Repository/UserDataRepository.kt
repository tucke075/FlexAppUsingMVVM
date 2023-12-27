package com.example.flexappusingmvvm.model.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flexappusingmvvm.model.Data.UserData
import com.google.firebase.firestore.FirebaseFirestore


class UserDataRepository {
    //initializing variables to talk to firebase firestore
    private val db = FirebaseFirestore.getInstance()

    fun saveDaysData(userData: UserData){
        val user: MutableMap<String, Any> = HashMap()
        user["email"] = userData.email
        user["name"] = userData.name
        user["title"] = userData.title
        user["shift"] = userData.shift
        db.collection("Days")
            .add(user)
            .addOnSuccessListener {
                Log.d("Data saved successfully", "$user")
            }
            .addOnFailureListener { e ->
                Log.d("Data not saved", "$e")
            }
    }
}
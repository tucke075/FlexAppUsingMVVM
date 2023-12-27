package com.example.flexappusingmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.flexappusingmvvm.R
import com.example.flexappusingmvvm.viewmodel.AuthViewModel
import com.example.flexappusingmvvm.databinding.ActivityLoginBinding
import com.example.flexappusingmvvm.model.Repository.UserAuthRepository
import com.example.flexappusingmvvm.viewmodel.AuthViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding : ActivityLoginBinding
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dont need since using data binding
        //setContentView(R.layout.activity_login)

        //linking layout to binding
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)



        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            viewModel.login(email,password)
        }

        //initializing viewModel with the AuthViewModel class to call its functions here
        val viewModelFactory = AuthViewModelFactory(UserAuthRepository(auth))
        viewModel = ViewModelProvider(this,viewModelFactory).get(AuthViewModel::class.java)

        //calling login function from AuthViewModel and checking if login is a success
        viewModel.signInStatus.observe(this){success ->
            if(success){
                startActivity(Intent( applicationContext, HomeActivity::class.java))
            }
            else{

            }
        }


    }

}
package com.example.flexappusingmvvm.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flexappusingmvvm.R
import com.example.flexappusingmvvm.databinding.ActivitySignupBinding
import com.example.flexappusingmvvm.model.Repository.UserAuthRepository
import com.example.flexappusingmvvm.model.Repository.UserDataRepository
import com.example.flexappusingmvvm.viewmodel.UserDataViewModel
import com.example.flexappusingmvvm.viewmodel.UserDataViewModelFactory
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: UserDataViewModel
    private var auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_signup)
        //link to data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)


        //variables to be sent to ViewModel
        var pairShift: Boolean = false
        var pairTitle: Boolean = false

        /*
        Two sets of pairs of checkboxes to indicate shift and title type
        Every click reinitialize the pair value to false to keep accurate readings
        If one part of pair gets clicked, othe gets unclicked
         */
        binding.CBDays.setOnCheckedChangeListener { _, isChecked ->
            pairShift = false
            if (isChecked) {
                binding.CBNights.isChecked = false
                pairShift = true
            }
        }
        binding.CBNights.setOnCheckedChangeListener { _, isChecked ->
            pairShift = false
            if (isChecked) {
                binding.CBDays.isChecked = false
                pairShift = true
            }
        }
        binding.CBRN.setOnCheckedChangeListener { _, isChecked ->
            pairTitle = false
            if (isChecked) {
                binding.CBNA.isChecked = false
                pairTitle = true
            }
        }
        binding.CBNA.setOnCheckedChangeListener { _, isChecked ->
            pairTitle = false
            if (isChecked) {
                binding.CBRN.isChecked = false
                pairTitle = true
            }
        }
        val viewModelFactory = UserDataViewModelFactory(UserDataRepository(), UserAuthRepository(auth))
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserDataViewModel::class.java)
        //signup button gets clicked, check if values on signup page are filled out correctly
            //By calling signup fun in view model
        binding.signup.setOnClickListener {
            //store info into userInfo from text boxes
            val email = binding.email.text.toString().trim()
            val name = binding.name.text.toString().trim()
            val password = binding.password.text.toString().trim()
            val repassword = binding.repassword.text.toString().trim()

            val days = binding.CBDays.isChecked
            val nights = binding.CBNights.isChecked
            val rn = binding.CBRN.isChecked
            val na = binding.CBNA.isChecked

            val titleShiftCombo = viewModel.checkTitle(days,nights,rn,na)

            viewModel.saveData(email, password, repassword, name,titleShiftCombo)
        }
        viewModel.signupSuccess.observe(this, Observer { isSuccess ->
            if(isSuccess)
                startActivity(Intent(this, HomeActivity::class.java))
            else
                Toast.makeText(this, "Failed to login",Toast.LENGTH_SHORT).show()
        })
    }
}
package com.example.flexappusingmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.flexappusingmvvm.R
import com.example.flexappusingmvvm.view.bot_nav_ui.DayNaFrag
import com.example.flexappusingmvvm.view.bot_nav_ui.DayRnFrag
import com.example.flexappusingmvvm.view.bot_nav_ui.NightNaFrag
import com.example.flexappusingmvvm.view.bot_nav_ui.NightRnFrag
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //variables for switching between fragments
        val dayRn = DayRnFrag()
        val dayNa = DayNaFrag()
        val nightRn = NightRnFrag()
        val nightNa = NightNaFrag()
        changeFragment(dayRn)
        findViewById<BottomNavigationView>(R.id.bot_nav).setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.DayRN -> {
                    changeFragment(dayRn)
                    true
                }

                R.id.DayNA -> {
                    changeFragment(dayNa)
                    true
                }

                R.id.NightRN -> {
                    changeFragment(nightRn)
                    true
                }

                R.id.NightNA -> {
                    changeFragment(nightNa)
                    true
                }

                else -> false
            }
        }
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }
}
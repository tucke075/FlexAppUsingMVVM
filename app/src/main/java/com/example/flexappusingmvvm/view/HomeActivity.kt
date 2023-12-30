package com.example.flexappusingmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.flexappusingmvvm.R
import com.example.flexappusingmvvm.databinding.ActivityHomeBinding
import com.example.flexappusingmvvm.view.bot_nav_ui.DayNaFrag
import com.example.flexappusingmvvm.view.bot_nav_ui.DayRnFrag
import com.example.flexappusingmvvm.view.bot_nav_ui.NightNaFrag
import com.example.flexappusingmvvm.view.bot_nav_ui.NightRnFrag


class HomeActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)


        val toolbar : Toolbar = binding.dNavToolBar
        val drawerLayout: DrawerLayout = binding.dNav

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //displays hamburger icon

        //variables for switching between fragments
        val dayRn = DayRnFrag()
        val dayNa = DayNaFrag()
        val nightRn = NightRnFrag()
        val nightNa = NightNaFrag()
        changeFragment(dayRn)

        binding.botNav.setOnItemSelectedListener { item ->
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
    //needed for navigation drawer
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }
}
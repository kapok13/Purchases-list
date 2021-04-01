package com.vb.purchaseslist.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayout
import com.vb.purchaseslist.R
import com.vb.purchaseslist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private val mainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        setSupportActionBar(mainBinding.mainToolbar)
        navController = findNavController(R.id.main_fragment_container)
        initFragmentNavigation()
    }


    private fun initFragmentNavigation() {
        mainBinding.mainTabsLayout.addOnTabSelectedListener(this)
    }


    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> navController.navigate(R.id.purchasesFragment)
            1 -> navController.navigate(R.id.purchasedFragment)
            2 -> navController.navigate(R.id.newPurchaseFragment)
        }
    }


    override fun onTabUnselected(tab: TabLayout.Tab?) {}


    override fun onTabReselected(tab: TabLayout.Tab?) {}


}
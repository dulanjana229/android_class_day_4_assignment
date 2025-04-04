package com.example.myapp.bottom_bar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapp.ChildrenFragment
import com.example.myapp.FemaleFragment
import com.example.myapp.FragmentAdapter
import com.example.myapp.MaleFragment
import com.example.myapp.PopularFragment
import com.example.myapp.R
import com.example.myapp.databinding.ActivityBottomBarBinding
import com.google.android.material.tabs.TabLayoutMediator

class BottomBarActivity : AppCompatActivity() {

    private lateinit var binding:ActivityBottomBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomBarBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val host =supportFragmentManager.findFragmentById(R.id.bottomFragmentContainerView) as NavHostFragment
        val navController = host.navController
        binding.bottomAppBar.setupWithNavController(navController)


        val fragmentList = listOf(
            PopularFragment(),
            MaleFragment(),
            FemaleFragment(),
            ChildrenFragment(),
        )

        val fragmentAdapter = FragmentAdapter(fragmentList, supportFragmentManager, lifecycle)

        binding.pager.adapter =fragmentAdapter

        TabLayoutMediator(binding.tabLayout,binding.pager){tab,position->
            tab.text = when(position){
                0->"Popular"
                1->"Male"
                2->"Female"
                3->"Children"
                else->""
            }
        }.attach()


    }
}
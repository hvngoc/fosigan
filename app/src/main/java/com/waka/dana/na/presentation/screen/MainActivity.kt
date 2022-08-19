package com.waka.dana.na.presentation.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.waka.dana.na.R
import com.waka.dana.na.databinding.ActivityMainBinding
import com.waka.dana.na.util.replaceFragment
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        val fragment = supportFragmentManager.findFragmentByTag(MainFragment.TAG)
        if (fragment == null) {
            replaceFragment(R.id.container, MainFragment.newInstance(), MainFragment.TAG)
        }
    }
}
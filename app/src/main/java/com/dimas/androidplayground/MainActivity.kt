package com.dimas.androidplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.dimas.androidplayground.adapter.MainPagerAdapter
import com.dimas.androidplayground.databinding.ActivityMainBinding
import com.dimas.androidplayground.screen.ChatFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeToolbar()
        initializeViewPager()
    }

    private fun initializeViewPager() {
        val adapter = MainPagerAdapter(this)
        adapter.addFragment(ChatFragment.newInstance())
        adapter.addFragment(ChatFragment.newInstance())
        adapter.addFragment(ChatFragment.newInstance())
        with(binding) {
            vpMain.adapter = adapter
            TabLayoutMediator(tabMain, vpMain) { tab, position ->
                when(position) {
                    0 -> tab.text = getString(R.string.label_chat)
                    1 -> tab.text = getString(R.string.label_calls)
                    2 -> tab.text = getString(R.string.label_status)
                }
            }.attach()
        }
    }

    private fun initializeToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.label_whatsapp_clone)
            setDisplayHomeAsUpEnabled(false)
        }
    }
}
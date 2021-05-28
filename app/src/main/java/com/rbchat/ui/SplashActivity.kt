package com.rbchat.ui

import android.content.Intent
import android.os.Bundle
import com.rbchat.common.BaseActivity
import com.rbchat.databinding.ActivitySplashBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        GlobalScope.launch {
            delay(2000)
            if (preferenceUtil.login) {
                val intent = Intent(this@SplashActivity , MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity , SignInActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
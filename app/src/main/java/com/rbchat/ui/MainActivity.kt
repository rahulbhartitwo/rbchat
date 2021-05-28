package com.rbchat.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rbchat.R
import androidx.databinding.DataBindingUtil.setContentView
import com.rbchat.common.BaseActivity
import com.rbchat.databinding.ActivityMainBinding
import com.rbchat.inerfaceUtils.ServiceController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

}
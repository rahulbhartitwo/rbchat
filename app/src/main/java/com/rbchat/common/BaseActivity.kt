package com.rbchat.common

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rbchat.db.SharedPreferenceUtil
import com.rbchat.inerfaceUtils.stuff

open class BaseActivity : AppCompatActivity() , stuff , View.OnClickListener {

    lateinit var preferenceUtil: SharedPreferenceUtil

    override fun onCreate(savedInstanceState: Bundle? , persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState , persistentState)
        preferenceUtil = SharedPreferenceUtil.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferenceUtil = SharedPreferenceUtil.getInstance(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            else -> {
            }
        }
    }

    override fun listener() {
    }

    override fun initialize() {
    }
}
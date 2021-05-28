package com.rbchat.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rbchat.db.SharedPreferenceUtil
import com.rbchat.inerfaceUtils.stuff

open class BaseFragment  : Fragment(), stuff {

    lateinit var preferenceUtil: SharedPreferenceUtil

    override fun onAttach(context: Context) {
        super.onAttach(context)
        preferenceUtil = context.let { SharedPreferenceUtil.getInstance(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater , container , savedInstanceState)
    }

    override fun listener() {
    }

    override fun initialize() {
    }

//    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
//        super.onViewCreated(view , savedInstanceState)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        Log.d("TAG", "onActivityCreated")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d("TAG", "onDestroy")
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("TAG", "onStart")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("TAG", "onStop")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("TAG", "onPause")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("TAG", "onResume")
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//        Log.d("TAG", "onDetach")
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        Log.d("TAG", "onDestroyView")
//    }
}
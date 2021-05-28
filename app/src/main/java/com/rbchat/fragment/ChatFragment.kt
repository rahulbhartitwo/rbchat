package com.rbchat.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rbchat.common.BaseFragment
import com.rbchat.databinding.FragmentChatBinding

class ChatFragment : BaseFragment() {

    var TAG = "TAG_ChatFragment"
    lateinit var binding: FragmentChatBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG , "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater , container: ViewGroup? , savedInstanceState: Bundle?): View {
        Log.d(TAG , "onCreateView")
        binding = FragmentChatBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        Log.d(TAG , "onViewCreated")
//        var navController : NavController = Navigation.findNavController(view)
//        buttonFinish.setOnClickListener{
//            navController.navigate(R.id.action_loginFragment_to_third)
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG , "onActivityCreated")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG , "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG , "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG , "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG , "onPause")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG , "onViewStateRestored")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG , "onResume")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG , "onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG , "onDestroyView")
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String , param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
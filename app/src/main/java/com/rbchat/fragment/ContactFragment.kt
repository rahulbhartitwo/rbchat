package com.rbchat.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.rbchat.R
import com.rbchat.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_third.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ContactFragment : BaseFragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
//        var navController: NavController = Navigation.findNavController(view)
//        buttonRestart.setOnClickListener {
//            var navOption = NavOptions.Builder().setPopUpTo(R.id.mainFragment , true).build()
//            navController.navigate(R.id.action_third_to_mainFragment , null , navOption)
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String , param2: String) =
            ContactFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1 , param1)
                    putString(ARG_PARAM2 , param2)
                }
            }
    }
}
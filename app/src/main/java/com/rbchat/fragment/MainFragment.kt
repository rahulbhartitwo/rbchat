package com.rbchat.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.rbchat.R
import com.rbchat.adapter.CHAT_PAGE_INDEX
import com.rbchat.adapter.CONTACT_PAGE_INDEX
import com.rbchat.adapter.ViewPagerPagerAdapter
import com.rbchat.common.BaseFragment
import com.rbchat.databinding.FragmentMainBinding
import com.rbchat.inerfaceUtils.ServiceController
import com.rbchat.utils.debugToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment() {
    var TAG = "TAG_MainFragment"
    lateinit var binding: FragmentMainBinding
    @Inject
    lateinit var db: ServiceController

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG , "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            Log.d(TAG , "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater , container: ViewGroup? , savedInstanceState: Bundle?): View {
        Log.d(TAG , "onCreateView")
        binding = FragmentMainBinding.inflate(inflater , container , false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerPagerAdapter(this)
        TabLayoutMediator(tabLayout , viewPager) { tab , position ->
            tab.setIcon(getTabIcon(position))
            tab.apply {
                text = getTabTitle(position)
                text
            }
        }.attach()
        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            CHAT_PAGE_INDEX -> R.drawable.ic_launcher_foreground
            CONTACT_PAGE_INDEX -> R.drawable.ic_launcher_foreground
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            CHAT_PAGE_INDEX -> getString(R.string.str_chat)
            CONTACT_PAGE_INDEX -> getString(R.string.str_contact)
            else -> null
        }
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        if (savedInstanceState == null)
            Log.d(TAG , "onViewCreated")
        initialize()
//        val navController: NavController = Navigation.findNavController(view)
//        val bundle = Bundle()
//        bundle.putString("id" , "1")
//        button.setOnClickListener{
//            navController.navigate(R.id.action_mainFragment_to_loginFragment, bundle)
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null)
            Log.d(TAG , "onActivityCreated")

        debugToast(requireContext() ,   preferenceUtil.email )

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState == null)
            Log.d(TAG , "onViewStateRestored")
             Log.d(TAG , "${savedInstanceState?.get("id")}")
    }

    override fun initialize() {
        super.initialize()

    }
//===============================LIFE CYCLE================================================
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
}
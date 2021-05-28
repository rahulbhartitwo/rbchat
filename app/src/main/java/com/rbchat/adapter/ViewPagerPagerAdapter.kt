package com.rbchat.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rbchat.fragment.ChatFragment
import com.rbchat.fragment.ContactFragment

const val CHAT_PAGE_INDEX = 0
const val CONTACT_PAGE_INDEX = 1

class ViewPagerPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: Map<Int , () -> Fragment> = mapOf(
        CHAT_PAGE_INDEX to { ChatFragment() } ,
        CONTACT_PAGE_INDEX to { ContactFragment() }
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
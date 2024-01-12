package com.bsuir.kovko.app.screens.app.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeAdapter (fragmentActivity: FragmentActivity, var role: String?) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return HomeForViewingPage2Fragment.newInstance(position)
    }
}
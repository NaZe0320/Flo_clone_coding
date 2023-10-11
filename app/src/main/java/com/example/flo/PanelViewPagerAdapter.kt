package com.example.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PanelViewPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private val fragmentList: ArrayList<Fragment> = arrayListOf()

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position % (fragmentList.size)]

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size - 1)
    }
}
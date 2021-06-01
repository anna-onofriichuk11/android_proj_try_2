package com.example.tryengapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager, vararg fragments: Pair<String, Fragment>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments_list = listOf(*fragments)

    override fun getItem(position: Int) = fragments_list[position].second

    override fun getCount() = fragments_list.size

    override fun getPageTitle(position: Int) = fragments_list[position].first
}
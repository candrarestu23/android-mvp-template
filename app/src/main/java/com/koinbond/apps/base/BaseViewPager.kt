package com.koinbond.apps.base

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class BaseViewPager<AFragment : BaseFragment>(fm: FragmentManager, private val fragmentList: Array<AFragment>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): AFragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].title
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}

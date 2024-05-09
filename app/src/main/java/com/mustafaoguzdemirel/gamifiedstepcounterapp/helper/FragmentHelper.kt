package com.hukukkarar.app.common.helper

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentHelper private constructor() {

    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment?,
        fragmentTag: String?,
        containerId: Int
    ) {
        val transaction = fragmentManager.beginTransaction()
        if (fragment != null) {
            if (fragmentManager.findFragmentByTag(fragmentTag) == null) transaction.add(
                containerId,
                fragment,
                fragmentTag
            ) else {
                if (!fragmentManager.findFragmentByTag(fragmentTag)!!.isAdded) transaction.add(
                    containerId,
                    fragment,
                    fragmentTag
                )
            }
            transaction.commit()
        }
    }

    fun removeFragment(fragmentManager: FragmentManager, TAG: String?) {
        val fragment = fragmentManager.findFragmentByTag(TAG)
        val transaction = fragmentManager.beginTransaction()
        if (fragment != null) {
            transaction.remove(fragment)
            transaction.commit()
        }
    }

    fun showFragment(fragmentManager: FragmentManager, fragmentTag: String?) {
        val fragment = fragmentManager.findFragmentByTag(fragmentTag)
        val transaction = fragmentManager.beginTransaction()
        if (fragment != null) transaction.show(fragment)
        transaction.commit()
    }

    fun hideFragment(fragmentManager: FragmentManager, fragmentTag: String?) {
        val fragment = fragmentManager.findFragmentByTag(fragmentTag)
        val transaction = fragmentManager.beginTransaction()
        if (fragment != null) transaction.hide(fragment)
        transaction.commit()
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment?, containerId: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment!!)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    companion object {
        var instance: FragmentHelper? = null
            get() {
                if (field == null) field = FragmentHelper()
                return field
            }
            private set
    }
}

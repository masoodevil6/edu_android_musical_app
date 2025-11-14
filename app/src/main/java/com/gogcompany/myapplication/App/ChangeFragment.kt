package com.gogcompany.myapplication.App

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.gogcompany.myapplication.R

class ChangeFragment(private val fragmentManager: FragmentManager) {

    fun replaceFragment(fragment: Fragment, back: Boolean, fragmentEl: Int = R.id.Frame_layout_main) {
        val trans = fragmentManager.beginTransaction()
        if (back) trans.addToBackStack(null)
        trans.replace(fragmentEl, fragment)
        trans.commit()
    }
}
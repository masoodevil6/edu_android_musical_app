package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.R

class SplashFragment(): Fragment() {

    lateinit var layout:View;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_splash , container , false);
        cast()
        setViews()
        return layout;
    }

    private fun setViews() {

    }

    private fun cast() {
        Handler()
            .postDelayed({
                ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(IntroFragment() , false , R.id.element_id_first_activity_frame_layout)
                         } ,
                3000
            )

    }

}
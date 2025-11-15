package com.gogcompany.myapplication.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.MainActivity
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



    private fun cast() {

    }

    private fun setViews() {
        if (!Base.sharePref.getIntro()){
            Handler()
                .postDelayed({
                    ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(IntroFragment() , false , R.id.element_id_first_activity_frame_layout)
                } , 3000)
        }
        else{

            if (!Base.sharePref.getLogin()){
                ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(LoginFragment() , false , R.id.element_id_first_activity_frame_layout)
            }
            else{
                startActivity(Intent(Base.activitySplash , MainActivity::class.java))
                Base.activitySplash.finish();
            }
        }
    }

}
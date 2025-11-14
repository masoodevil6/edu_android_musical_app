package com.gogcompany.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.Fragment.SplashFragment

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        Base.activitySplash = this;

        ChangeFragment(supportFragmentManager).replaceFragment(SplashFragment() , false , R.id.element_id_first_activity_frame_layout);



    }
}
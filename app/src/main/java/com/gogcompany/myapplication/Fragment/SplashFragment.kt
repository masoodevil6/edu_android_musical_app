package com.gogcompany.myapplication.Fragment

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.SnackBarCustom
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
        setViews()
        return layout;
    }



    private fun setViews() {
        if (isConnected()){
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
        else{
            this.showSnack();
        }
    }


    private fun showSnack(){
        SnackBarCustom(Base.activitySplash , Base.activitySplash.window.decorView)
            .setTitle("دسترسی به اینترنت")
            .setMessage("اینترنت گوشی یا شبکه گوشی را بررسی کنید")
            .setOnFirstClick("تلاش مجدد"){
                setViews();
            }
            .show();
    }


    private fun isConnected() : Boolean{
        val connectivityManager =  Base.activitySplash.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)

        val isWifi = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true
        val isMobile = capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true

        return isWifi || isMobile
    }

}
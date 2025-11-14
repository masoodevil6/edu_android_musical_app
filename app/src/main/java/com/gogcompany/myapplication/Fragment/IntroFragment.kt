package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.gogcompany.myapplication.Adapter.AdapterIntro
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.Model.Lottie
import com.gogcompany.myapplication.R
import com.google.android.material.tabs.TabLayout

class IntroFragment() : Fragment() {

    lateinit var layout: View;
    lateinit var tabLayout: TabLayout;
    lateinit var viewPager: ViewPager;
    lateinit var btnLogin: Button;

    var list = ArrayList<Lottie>();
    lateinit var adapterIntro : AdapterIntro;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_intro , container , false);
        cast();
        setViews();
        return layout;
    }

    private fun cast() {
        tabLayout = layout.findViewById(R.id.element_id_intro_fragment_tab_layout)
        viewPager = layout.findViewById(R.id.element_id_intro_fragment_view_pager)
        btnLogin = layout.findViewById(R.id.element_id_intro_fragment_btn_submit)
    }

    private fun setViews() {

        list.add(Lottie("اضافه کردن موزیک به صورت دلخواه" ,  R.raw.add_music));
        list.add(Lottie("نمایش موزیک ویدئو شما" ,            R.raw.video));
        list.add(Lottie("پخش موزیک ویدئو" ,                  R.raw.video__palyer));
        list.add(Lottie("پحش موزیک انلاین" ,                  R.raw.music));


        adapterIntro = AdapterIntro(Base.activitySplash, list);
        viewPager.adapter = adapterIntro;
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab!!.position == list.size-1){
                    tabLayout.visibility = View.GONE;
                    btnLogin.visibility = View.VISIBLE;
                }
                else{
                    tabLayout.visibility = View.VISIBLE;
                    btnLogin.visibility = View.GONE;
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        btnLogin.setOnClickListener{
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(LoginFragment() , false , R.id.element_id_first_activity_frame_layout);
        }


    }


}
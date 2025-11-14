package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.gogcompany.myapplication.Model.Lottie
import com.gogcompany.myapplication.R

class AdapterIntro(
    private val context: Context ,
    private val list: ArrayList<Lottie>
) : PagerAdapter() {

    override fun getCount(): Int {
        return list.size;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout : View =                    LayoutInflater.from(context).inflate(R.layout.layout_lottie , container , false);
        val lottie : LottieAnimationView =     layout.findViewById(R.id.element_id_intro_recycler_view_lottie_anim);
        val description : TextView =           layout.findViewById(R.id.element_id_intro_recycler_view_txt_description);

        val data = list.get(position);
        description.text = data.description;
        lottie.setAnimation(data.lottie!!);

        container.addView(layout);

        return layout;
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view;
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View);
    }


}
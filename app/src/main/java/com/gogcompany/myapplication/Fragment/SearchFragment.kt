package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.R

class SearchFragment(): Fragment() {

    lateinit var layout:               View;
    lateinit var relTypeSingerIran:    View;
    lateinit var relTypeSingerForeign: View;
    lateinit var relTypeMusicAdd:      View;
    lateinit var relTypeMusic:         View;
    lateinit var relTypeVideo:         View;
    lateinit var btnBack:              View;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layout = inflater.inflate(R.layout.fragment_search , container , false);
        cast();
        setViews();
        return layout;
    }



    private fun cast() {
        relTypeSingerIran =     layout.findViewById(R.id.element_id_favorite_search_card_type_singer_iran);
        relTypeSingerForeign =  layout.findViewById(R.id.element_id_favorite_search_card_type_singer_foreign);
        relTypeMusicAdd =       layout.findViewById(R.id.element_id_favorite_search_card_type_music_add);
        relTypeMusic =          layout.findViewById(R.id.element_id_favorite_search_card_type_music);
        relTypeVideo =          layout.findViewById(R.id.element_id_favorite_search_card_type_video);
        btnBack =               layout.findViewById(R.id.element_id_favorite_search_card_image_btn_back);
    }


    private fun setViews() {
        relTypeSingerIran.setOnClickListener {
            setFragment(_FRAGMENT_TYPE_SINGER_IRAN);
        }
        relTypeSingerForeign.setOnClickListener {
            setFragment(_FRAGMENT_TYPE_SINGER_FOREGIN);
        }
        relTypeMusicAdd.setOnClickListener {
            setFragment(_FRAGMENT_TYPE_MUSIC_ADD);
        }
        relTypeMusic.setOnClickListener {
            setFragment(_FRAGMENT_TYPE_MUSIC);
        }
        relTypeVideo.setOnClickListener {
            setFragment(_FRAGMENT_TYPE_VIDEO);
        }

        btnBack.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , false);
            }
        })
    }

    private fun setFragment(fragmentType: Int){
        _fragmentType = fragmentType;
        ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(DetailSearchFragment() , false)
    }

    companion object{
        var _fragmentType: Int? = null;
        var _FRAGMENT_TYPE_SINGER_IRAN = 0;
        var _FRAGMENT_TYPE_SINGER_FOREGIN = 1;
        var _FRAGMENT_TYPE_MUSIC_ADD = 2;
        var _FRAGMENT_TYPE_MUSIC = 3;
        var _FRAGMENT_TYPE_VIDEO = 4;
    }
}
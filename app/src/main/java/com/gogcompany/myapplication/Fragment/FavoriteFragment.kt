package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.keyFavType
import com.gogcompany.myapplication.R

class FavoriteFragment : Fragment() {

    lateinit var layout: View;
    lateinit var imageBack: ImageView;
    lateinit var relListSinger: RelativeLayout;
    lateinit var relListMusic: RelativeLayout;
    lateinit var relListVideo: RelativeLayout;
    lateinit var relListClient: RelativeLayout;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_favorite , container , false);
        cast();
        setViews();
        return layout;
    }


    private fun cast() {
        imageBack = layout.findViewById(R.id.element_id_fragment_favorite_image_back);
        relListSinger = layout.findViewById(R.id.element_id_fragment_favorite_rel_item_singer);
        relListMusic = layout.findViewById(R.id.element_id_fragment_favorite_rel_item_music);
        relListVideo = layout.findViewById(R.id.element_id_fragment_favorite_rel_item_video);
        relListClient = layout.findViewById(R.id.element_id_fragment_favorite_rel_item_client);
    }


    private fun setViews() {
        relListSinger.setOnClickListener {
            //gotoDetailFavorite(1);
            setFragment(1)
        }
        relListMusic.setOnClickListener {
            //gotoDetailFavorite(2);
            setFragment( 2)

        }
        relListVideo.setOnClickListener {
            //gotoDetailFavorite(3);
            setFragment(3)
        }
        relListClient.setOnClickListener {
            //gotoDetailFavorite(4);
            setFragment(4)
        }
    }


    /*private fun gotoDetailFavorite(categoryFavType: Int){
        var bundle = Bundle();
        bundle.putInt(keyFavType , categoryFavType)
        var df = DetailFavoriteFragment();
        df.arguments = bundle;
        ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(df , true);
    }*/

    fun setFragment(idSort: Int){
        idItem = idSort;
        var df = DetailFavoriteFragment();
        ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(df , true);
    }

    companion object{
        var idItem: Int?= 0;
    }

}
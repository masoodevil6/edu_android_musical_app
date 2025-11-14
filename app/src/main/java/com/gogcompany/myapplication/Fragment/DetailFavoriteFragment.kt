package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterAddMusicFav
import com.gogcompany.myapplication.Adapter.AdapterFavArtists
import com.gogcompany.myapplication.Adapter.AdapterFavMusic
import com.gogcompany.myapplication.Adapter.AdapterFavVideo
import com.gogcompany.myapplication.Adapter.AdapterVideo
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.DataBase.FavArtist.FavArtist
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusic
import com.gogcompany.myapplication.DataBase.FavVideo.FavVideo
import com.gogcompany.myapplication.DataBase.MusicAddFav.MusicAddFav
import com.gogcompany.myapplication.R

class DetailFavoriteFragment(): Fragment() {

    lateinit var layout: View;
    lateinit var titleCategoryFav: TextView;
    lateinit var recList: RecyclerView;

    lateinit var adapterFavMusic: AdapterFavMusic;
    var listMusics = ArrayList<FavMusic>();

    lateinit var adapterFavArtist: AdapterFavArtists;
    var listArtist = ArrayList<FavArtist>();

    lateinit var adapterFavVideo: AdapterFavVideo;
    var listVideo = ArrayList<FavVideo>();

    lateinit var adapterAddMusicFav: AdapterAddMusicFav;
    var listMyMusic = ArrayList<MusicAddFav>();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_detail_fav, container , false);
        cast();
        setViews();
        return layout;
    }

    private fun cast() {
        titleCategoryFav = layout.findViewById(R.id.element_id_fragment_favorite_detail_txt_title_category_fav);
        recList = layout.findViewById(R.id.element_id_fragment_favorite_detail_txt_recycler_view_list);
    }


    private fun setViews() {
        recList.setHasFixedSize(true);
        recList.layoutManager = GridLayoutManager(Base.activity , 2);

        if (FavoriteFragment.idItem == 1){
            Base.dbApp!!.favMusicDao().getAll().observe(requireActivity() , {
                    musicFav->
                listMusics.clear();
                listMusics.addAll(musicFav);
                adapterFavMusic.notifyDataSetChanged();
            })
            adapterFavMusic = AdapterFavMusic(Base.activity , listMusics , requireActivity().supportFragmentManager);
            recList.adapter = adapterFavMusic;
        }
        else if (FavoriteFragment.idItem == 2){
            Base.dbApp!!.favArtistDao().getAll().observe(requireActivity() , {
                    favArtist->
                listArtist.clear();
                listArtist.addAll(favArtist);
                adapterFavArtist.notifyDataSetChanged();
            })
            adapterFavArtist = AdapterFavArtists(Base.activity , listArtist , requireActivity().supportFragmentManager);
            recList.adapter = adapterFavArtist;
        }
        else if (FavoriteFragment.idItem == 3){
            Base.dbApp!!.favVideoDao().getAllVideo().observe(requireActivity() , {
                    facVideo->
                listVideo.clear();
                listVideo.addAll(facVideo);
                adapterFavVideo.notifyDataSetChanged();
            })
            adapterFavVideo = AdapterFavVideo(Base.activity , listVideo , requireActivity().supportFragmentManager);
            recList.adapter = adapterFavArtist;
        }
        else if (FavoriteFragment.idItem == 4){
            Base.dbApp!!.musicAddFavDao().getAll().observe(requireActivity() , {
                    myMusicFav->
                listMyMusic.clear();
                listMyMusic.addAll(myMusicFav);
                adapterAddMusicFav.notifyDataSetChanged();
            })
            adapterAddMusicFav = AdapterAddMusicFav(Base.activity , listMyMusic , requireActivity().supportFragmentManager);
            recList.adapter = adapterAddMusicFav;
        }

    }
}
package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterMoreArtist
import com.gogcompany.myapplication.Adapter.AdapterMoreMusic
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.KeyId
import com.gogcompany.myapplication.App.KeyMore
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.R

class MoreFragment():Fragment() {

    lateinit var layout: View;
    lateinit var recyclerView: RecyclerView;
    lateinit var adapterArtists: AdapterMoreArtist;
    lateinit var adapterMusic: AdapterMoreMusic;
    lateinit var txtToolbar: TextView;
    var listMusicMore = ArrayList<Musics>();
    var listArtistsMore = ArrayList<Artists>();
    var category: Int = 0;
    var type: Int = 0;
    lateinit var imgBack: ImageView;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layout = inflater.inflate(R.layout.fragment_more , container , false);
        cast();
        return layout;
    }

    private fun cast(){
        txtToolbar = layout.findViewById(R.id.txt_toolbar_more);
        imgBack = layout.findViewById(R.id.back_more);
        category= arguments?.getInt(KeyMore) ?: 0
        type= arguments?.getInt(KeyId)!!
        recyclerView = layout.findViewById(R.id.rec_more)
        recyclerView.setHasFixedSize(true);
        recyclerView.layoutManager=LinearLayoutManager(Base.activity);



        if (type==0){
            Base.dbApp!!.musicDao().getCategoryMusic(category).observe(requireActivity(),
                {music->
                    listMusicMore.clear();
                    listMusicMore.addAll(music);
                    adapterMusic.notifyDataSetChanged();
                });
            adapterMusic = AdapterMoreMusic(Base.activity , listMusicMore , requireActivity().supportFragmentManager);
            recyclerView.adapter =adapterMusic;

            if (category == 0){
                txtToolbar.text = "جدید ترین ها";
            }
            else{
                txtToolbar.text = "محبوب ترین ها";
            }

        }
        else{
            Base.dbApp!!.artistDao().getCategory(category).observe(requireActivity(),
                {artist->
                    listArtistsMore.clear();
                    listArtistsMore.addAll(artist);
                    adapterArtists.notifyDataSetChanged();
                });
            adapterArtists = AdapterMoreArtist(Base.activity , listArtistsMore , requireActivity().supportFragmentManager);
            recyclerView.adapter =adapterArtists;

            if (category == 0){
                txtToolbar.text = "ایرانی";
            }
            else{
                txtToolbar.text = "خارجی";
            }

        }


        imgBack.setOnClickListener(){
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , true);
        }
    }

}
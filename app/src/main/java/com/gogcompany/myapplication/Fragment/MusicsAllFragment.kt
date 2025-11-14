package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterAllMusic
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.R

class MusicsAllFragment() : Fragment() {

    lateinit var layout: View;
    lateinit var btnBack: ImageView;
    lateinit var recAllMusic: RecyclerView;

    var list = ArrayList<Musics>();
    lateinit var adapter: AdapterAllMusic;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_music_all , container , false);
        cast();
        setViews();
        return layout;
    }

    private fun cast() {
        btnBack = layout.findViewById(R.id.element_id_fragment_all_music_image_btn_back);
        recAllMusic = layout.findViewById(R.id.element_id_fragment_all_music_recycler_view_list_music);
    }

    private fun setViews() {
        btnBack.setOnClickListener {
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , false);
        }

        recAllMusic.setHasFixedSize(true);

        Base.dbApp!!.musicDao().getAllMusic().observe(requireActivity() , {
            musics->
            list.clear();
            list.addAll(musics);
            adapter.notifyDataSetChanged();
        })

        adapter = AdapterAllMusic(Base.activity , list , requireActivity().supportFragmentManager);
        recAllMusic.adapter=adapter;

    }
}
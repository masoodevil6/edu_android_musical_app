package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterAddMusic
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAdd
import com.gogcompany.myapplication.R
import java.util.Objects

class AddFragment() : Fragment() {

    lateinit var layout: View;
    lateinit var btnBack: ImageView;
    lateinit var recList: RecyclerView;
    lateinit var btnAdd: RelativeLayout;

    var list = ArrayList<MusicAdd>();
    lateinit var adapterAddMusic : AdapterAddMusic;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout=inflater.inflate(R.layout.fragment_add , container ,false);
        cast()
        setViews();
        return layout;
    }


    private fun cast() {
        btnBack = layout.findViewById(R.id.element_id_fragment_add_image_btn_back)
        recList = layout.findViewById(R.id.element_id_fragment_add_recycler_view_list)
        btnAdd = layout.findViewById(R.id.element_id_fragment_add_relative_btn_add)
    }

    private fun setViews() {
        btnBack.setOnClickListener {
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , false);
        }

        recList.setHasFixedSize(true);
        recList.layoutManager = LinearLayoutManager(Base.activity);
        Base.dbApp!!.musicAddDao().getAll().observe(requireActivity()  , {
            musicAdds->
            list.clear();
            list.addAll(musicAdds);
            adapterAddMusic.notifyDataSetChanged()
        })

        adapterAddMusic = AdapterAddMusic(Base.activity , list , requireActivity().supportFragmentManager)
        recList.adapter = adapterAddMusic;

        btnAdd.setOnClickListener {
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(AddMusicFragment() , true);
            AddMusicFragment.addMusicFun(object: AddMusicFragment.IAddMusic{
                override fun onAddMusic(
                    artistName: String,
                    musicName: String,
                    musicUrl: String
                ){
                    val musicAdd = MusicAdd();
                    musicAdd.artistName = artistName;
                    musicAdd.musicName = musicName;
                    musicAdd.musicUrl = musicUrl;
                    Base.dbApp!!.musicAddDao().insertMusic(musicAdd)
                }
            })
        }
    }


}
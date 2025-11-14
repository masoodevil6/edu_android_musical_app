package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterVideo
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.R

class VideoAllFragment() : Fragment() {

    lateinit var layout: View;
    lateinit var btnBack: ImageView;
    lateinit var recList: RecyclerView;
    lateinit var adapter: AdapterVideo;
    var listVideo = ArrayList<Video>();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_video_all , container , false);
        cast();
        setViews();
        return layout;
    }

    private fun cast() {
        btnBack = layout.findViewById(R.id.back_video);
        recList = layout.findViewById(R.id.rec_video);
    }

    private fun setViews() {

        btnBack.setOnClickListener {
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , false);
        }

        recList.setHasFixedSize(true);
        recList.layoutManager = LinearLayoutManager(Base.activity);

        Base.dbApp.VideoDao().getAllVideo().observe(requireActivity() , {
                video->
            listVideo.clear();
            listVideo.addAll(video);
            adapter.notifyDataSetChanged();
        })

        adapter = AdapterVideo(Base.activity , listVideo , requireActivity().supportFragmentManager);
        recList.adapter = adapter;

    }

}
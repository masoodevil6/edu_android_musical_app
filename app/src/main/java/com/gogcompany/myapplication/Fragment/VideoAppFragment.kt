package com.gogcompany.myapplication.Fragment

import android.annotation.SuppressLint
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterVideoApp
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.Model.VideoApp
import com.gogcompany.myapplication.R

class VideoAppFragment(): Fragment() {


    lateinit var layout: View;
    lateinit var btnBack : ImageView;
    lateinit var recListVideo: RecyclerView;

    lateinit var adapter: AdapterVideoApp;
    var list = ArrayList<VideoApp>();


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_video_app , container , false);
        cast()
        setViews();
        return layout;
    }

    private fun cast() {
        btnBack = layout.findViewById(R.id.element_id_fragment_video_app_image_btn_back);
        recListVideo = layout.findViewById(R.id.element_id_fragment_video_app_recycler_view_videos);
    }

    @SuppressLint("Range")
    private fun setViews() {
        val contentResolver = Base.activity.contentResolver;
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        val cursor = contentResolver.query(uri , null , null ,null , null);
        if (cursor!!.moveToFirst()){
            do {
                val videoName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE))
                val videoPath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))
                var videoImage = ThumbnailUtils.createVideoThumbnail(videoPath , MediaStore.Images.Thumbnails.MINI_KIND)

                list.add(VideoApp(videoName , videoPath , videoImage!!));
            }while (cursor.moveToNext());
        }

        recListVideo.setHasFixedSize(true);
        recListVideo.layoutManager = GridLayoutManager(Base.activity , 2);
        adapter = AdapterVideoApp(Base.activity , list , requireActivity().supportFragmentManager)
        recListVideo.adapter = adapter;


    }

}
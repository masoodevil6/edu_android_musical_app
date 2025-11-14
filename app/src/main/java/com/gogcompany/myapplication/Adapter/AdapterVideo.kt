package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.keyVideoId
import com.gogcompany.myapplication.DataBase.FavVideo.FavVideo
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.Fragment.DetailVideoFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso

class AdapterVideo(
    private val context: Context ,
    private val list : ArrayList<Video>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterVideo.Holder>(){

    var layoutMain: Int? = null;
    var idMain: Int = 0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVideo.Holder {
        layoutMain = R.layout.item_video2
        if (idMain%2 == 0){
            layoutMain = R.layout.item_video;
        }
        val layout = LayoutInflater.from(context).inflate(layoutMain!! , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterVideo.Holder, position: Int) {
        idMain = position+1;

        val itemVideo = list.get(position);

        Picasso.get().load(itemVideo.videoLinkImage).into(holder.image);
        holder.videoName.text = itemVideo.videoName;
        holder.videoTime.text = itemVideo.videoTime;


        if (Base.dbApp!!.favVideoDao().isFavorite(itemVideo?.videoId!!) == 1){
            holder.fav.setImageResource(R.drawable.baseline_favorite_24)
        }
        else{
            holder.fav.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        holder.fav.setOnClickListener {
            if (Base.dbApp!!.favVideoDao().isFavorite(itemVideo?.videoId!!) != 1){
                val favVideo = FavVideo();
                favVideo.videoId = favVideo.videoId;
                favVideo.artistId = favVideo.artistId;
                favVideo.videoName = favVideo.videoName;
                favVideo.videoLinkImage = favVideo.videoLinkImage;
                favVideo.videoLinkVideo = favVideo.videoLinkVideo;
                favVideo.videoTime = favVideo.videoTime;
                Base.dbApp!!.favVideoDao().insertVideo(favVideo);
                holder.fav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                val favVideo = FavVideo();
                favVideo.videoId = favVideo.videoId;
                favVideo.artistId = favVideo.artistId;
                favVideo.videoName = favVideo.videoName;
                favVideo.videoLinkImage = favVideo.videoLinkImage;
                favVideo.videoLinkVideo = favVideo.videoLinkVideo;
                favVideo.videoTime = favVideo.videoTime;
                Base.dbApp!!.favVideoDao().deleteVideo(favVideo);
                holder.fav.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }


        holder.itemView.setOnClickListener {
            val bundle =Bundle();
            bundle.putInt(keyVideoId , itemVideo.videoId!!);
            val fragment = DetailVideoFragment();
            fragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment(fragment , true);
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(videHolder: View): RecyclerView.ViewHolder(videHolder){
        val image: ImageView =videHolder.findViewById(R.id.element_id_recycler_video_image_video);
        val fav: ImageView =videHolder.findViewById(R.id.element_id_recycler_video_image_fav);
        val videoName: TextView =videHolder.findViewById(R.id.element_id_recycler_video_txt_title_video);
        val videoTime: TextView =videHolder.findViewById(R.id.element_id_recycler_video_txt_time_video);
    }

}
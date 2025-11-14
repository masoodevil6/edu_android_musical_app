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
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.keyVideoId
import com.gogcompany.myapplication.DataBase.FavVideo.FavVideo
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.Fragment.DetailVideoFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso

class AdapterVideoSearch(
    private val context: Context,
    private val list: List<Video>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterVideoSearch.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVideoSearch.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_search_video , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterVideoSearch.Holder, position: Int) {
        val itemVideo = list.get(position);
        Picasso.get().load(itemVideo.videoLinkImage).into(holder.image);
        holder.videoName.text = itemVideo.videoName;

        holder.itemView.setOnClickListener {
            val bundle = Bundle();
            bundle.putInt(keyVideoId , itemVideo.videoId!!);
            val fragment = DetailVideoFragment();
            fragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment(fragment , true);
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView= itemView.findViewById(R.id.element_id_recycler_video_search_image_video);
        val videoName : TextView = itemView.findViewById(R.id.element_id_recycler_video_search_txt_name_video);
    }

}
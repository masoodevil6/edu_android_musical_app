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
import com.gogcompany.myapplication.App.keyVideoImage
import com.gogcompany.myapplication.App.keyVideoName
import com.gogcompany.myapplication.App.keyVideoPath
import com.gogcompany.myapplication.Fragment.DetailVideoAppFragment
import com.gogcompany.myapplication.Fragment.DetailVideoFragment
import com.gogcompany.myapplication.Model.VideoApp
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso

class AdapterVideoApp(
    private val context: Context,
    private val list : ArrayList<VideoApp>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterVideoApp.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVideoApp.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_video_app , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterVideoApp.Holder, position: Int) {
        var videoItem = list.get(position);

        holder.image.setImageBitmap(videoItem.videoImage);
        holder.name.text = videoItem.videoName;

        holder.itemView.setOnClickListener {
            val bundle = Bundle();
            bundle.putString(keyVideoName , videoItem.videoName!!);
            bundle.putString(keyVideoPath , videoItem.videoPath!!);
            val fragment = DetailVideoAppFragment();
            fragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment(fragment , true);
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val name : TextView = itemView.findViewById(R.id.element_id_recycler_video_app_txt_name_video)
        val image : ImageView = itemView.findViewById(R.id.element_id_recycler_video_app_image_video)
    }

}
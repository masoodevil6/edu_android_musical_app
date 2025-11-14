package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.keyMusicId
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.Fragment.DetailMusicFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.zip.Inflater

class AdapterItemMusic(
    private val context: Context ,
    private val list: ArrayList<Musics> ,
    private val fragmentManager: FragmentManager
    ): RecyclerView.Adapter<AdapterItemMusic.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItemMusic.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_music_detail , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterItemMusic.Holder, position: Int) {
        var itemMusic = list.get(position);

        holder.txtNameMusic.text = itemMusic.musicName;
        holder.txtTimeMusic.text = itemMusic.musicTime;
        Picasso.get().load(itemMusic.musicLinkImage).into(holder.iamge);

        holder.rel.setOnClickListener {
            val bundle = Bundle();
            bundle.putInt(keyMusicId , itemMusic.musicId!!)
            val detailMusicFragment = DetailMusicFragment();
            detailMusicFragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment(detailMusicFragment , false);
        }

        holder.btnDownload.setOnClickListener{

        }

        holder.btnFav.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rel: RelativeLayout = itemView.findViewById(R.id.rel_music_item);
        val txtNameMusic:TextView = itemView.findViewById(R.id.txt_name_music_item);
        val txtTimeMusic:TextView = itemView.findViewById(R.id.txt_time_music_item);
        var btnDownload:ImageView = itemView.findViewById(R.id.btn_download_music_item);
        var btnFav:ImageView = itemView.findViewById(R.id.btn_fav_music_item);
        var iamge:CircleImageView = itemView.findViewById(R.id.image_music_item);
    }
}
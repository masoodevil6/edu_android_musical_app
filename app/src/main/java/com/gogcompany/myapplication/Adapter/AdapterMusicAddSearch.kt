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
import com.gogcompany.myapplication.App.keyMyMusicId
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAdd
import com.gogcompany.myapplication.DataBase.MusicAddFav.MusicAddFav
import com.gogcompany.myapplication.Fragment.DetailMusicAddFragment
import com.gogcompany.myapplication.R

class AdapterMusicAddSearch(
    private val context: Context ,
    private val list: List<MusicAdd>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterMusicAddSearch.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMusicAddSearch.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_add_music_fav , parent , false);
        return Holder(layout)
    }

    override fun onBindViewHolder(holder: AdapterMusicAddSearch.Holder, position: Int) {
        val itemMusicAddFav = list.get(position);

        holder.musicName.text = itemMusicAddFav.musicName;
        holder.artistName.text = itemMusicAddFav.artistName;

        holder.itemView.setOnClickListener {
            val bundle = Bundle();
            bundle.putInt(keyMyMusicId , itemMusicAddFav.id!!);
            val fragment = DetailMusicAddFragment();
            fragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment( fragment, true);
        }


        if (Base.dbApp!!.musicAddFavDao().isFav(itemMusicAddFav.id!!) == 1){
            holder.btnFav.setImageResource(R.drawable.baseline_favorite_24)
        }
        else{
            holder.btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        holder.btnFav.setOnClickListener {
            if (Base.dbApp!!.musicAddFavDao().isFav(itemMusicAddFav.id!!) != 1){
                val musicAddFav = MusicAddFav();
                musicAddFav.id = itemMusicAddFav.id;
                musicAddFav.musicName = itemMusicAddFav.musicName;
                musicAddFav.musicUrl = itemMusicAddFav.musicUrl;
                musicAddFav.artistName = itemMusicAddFav.artistName;
                Base.dbApp!!.musicAddFavDao().insertMusicAddFav(musicAddFav)
                holder.btnFav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                val musicAddFav = MusicAddFav();
                musicAddFav.id = itemMusicAddFav.id;
                musicAddFav.musicName = itemMusicAddFav.musicName;
                musicAddFav.musicUrl = itemMusicAddFav.musicUrl;
                musicAddFav.artistName = itemMusicAddFav.artistName;
                Base.dbApp!!.musicAddFavDao().deleteMusicAddFav(musicAddFav)
                holder.btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val musicName : TextView = itemView.findViewById(R.id.element_id_recycler_item_add_music_fav_txt_music_name);
        val artistName : TextView = itemView.findViewById(R.id.element_id_recycler_item_add_music_fav_txt_artist_name);
        val btnFav : ImageView = itemView.findViewById(R.id.element_id_recycler_item_add_music_fav_image_btn_fav);
    }
}
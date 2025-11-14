package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.keyMusicId
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusic
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.Fragment.DetailMusicFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AdapterFavMusic(
    private val context: Context ,
    private val list: ArrayList<FavMusic> ,
    private val fragmentManager: FragmentManager)
    : RecyclerView.Adapter<AdapterFavMusic.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFavMusic.Holder {
        val viewHolder: View = LayoutInflater.from(context).inflate(R.layout.item_fav_music , parent ,  false);
        return Holder(viewHolder);
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: AdapterFavMusic.Holder, position: Int) {
       val item: FavMusic = list.get(position);
        holder.txtMusicName.text = "نام موزیک" + item.musicName;

        Picasso.get().load(item.musicLinkImage).into(holder.image);

        var artist: Artists = Base.dbApp.artistDao().getInfo(item.artistId);
        holder.txtArtistName.text = "نام خواننده" + artist.artistName;

        holder.item.setOnClickListener {
            val bundle = Bundle();
            bundle.putInt(keyMusicId , item.musicId!!)
            val detailMusicFragment = DetailMusicFragment();
            detailMusicFragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment(detailMusicFragment , false);
        }

    }

    class Holder(viewHolder : View) : RecyclerView.ViewHolder(viewHolder){
        val image: CircleImageView = viewHolder.findViewById(R.id.element_id_adapter_favorite_item_music_cir_image_fav_music);
        val txtArtistName: TextView = viewHolder.findViewById(R.id.element_id_adapter_favorite_item_txt_artist_name_fav_music);
        val txtMusicName: TextView = viewHolder.findViewById(R.id.element_id_adapter_favorite_item_txt_music_name_fav_music);
        val item: RelativeLayout = viewHolder.findViewById(R.id.element_id_adapter_favorite_item_music_rel_fav_music);

    }
}
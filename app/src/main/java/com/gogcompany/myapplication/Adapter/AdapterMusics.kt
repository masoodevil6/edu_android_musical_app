package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.Fragment.DetailsArtistFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AdapterMusics(
    private val context:          Context ,
    private val list:             ArrayList<Musics> ,
    private val fragmentManager:  FragmentManager
    ) : RecyclerView.Adapter<AdapterMusics.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMusics.Holder {
        var layout = LayoutInflater.from(context).inflate(R.layout.item_music_new , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterMusics.Holder, position: Int) {
        var itemMusic:Musics = list.get(position);

        holder.music.text= itemMusic.musicName;

        Picasso.get().load(itemMusic.musicLinkImage).into(holder.image);

        var artist:Artists = Base.dbApp.artistDao().getInfo(itemMusic.artistId);
        holder.artist.text = artist.artistName;

        holder.itemView.setOnClickListener{
            val bundle = Bundle();
            bundle.putInt("keyMusicId" , itemMusic.musicId!!);
            val detailMusicFragment = DetailsArtistFragment();
            detailMusicFragment.arguments = bundle;
            ChangeFragment(fragmentManager).replaceFragment(detailMusicFragment , false);
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(itemView : View):RecyclerView.ViewHolder(itemView){
        var image : CircleImageView = itemView.findViewById(R.id.image_music_new);
        var artist : TextView = itemView.findViewById(R.id.txt_name_artist_new);
        var music : TextView = itemView.findViewById(R.id.txt_name_music_new);
    }
}
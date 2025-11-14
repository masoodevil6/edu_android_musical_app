package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusic
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AdapterAllMusic(
    private val context: Context ,
    private val list : ArrayList<Musics> ,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AdapterAllMusic.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAllMusic.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_all_music , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterAllMusic.Holder, position: Int) {
        val itemMusic = list.get(position);
        val artistData = Base.dbApp!!.artistDao().getInfo(itemMusic.musicId);

        holder.musicName.text= itemMusic.musicName;
        holder.artistName.text = artistData.artistName;
        Picasso.get().load(itemMusic.musicLinkImage).into(holder.image);


        if (Base.dbApp!!.favMusicDao().isFavorite(itemMusic.musicId!!) == 1){
            holder.btnFav.setImageResource(R.drawable.baseline_favorite_24)
        }
        else{
            holder.btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        holder.btnFav.setOnClickListener{
            if (Base.dbApp!!.favMusicDao().isFavorite(itemMusic.musicId!!) != 1){
                val favMusic = FavMusic();
                favMusic.musicId = itemMusic!!.musicId;
                favMusic.artistId = itemMusic!!.artistId;
                favMusic.musicIdSort = itemMusic!!.musicIdSort;
                favMusic.musicCategory = itemMusic!!.musicCategory;
                favMusic.musicLinkMusic = itemMusic!!.musicLinkMusic;
                favMusic.musicLinkImage = itemMusic!!.musicLinkImage;
                favMusic.musicName = itemMusic!!.musicName;
                favMusic.musicTime = itemMusic!!.musicTime;
                Base.dbApp!!.favMusicDao().insertFavMusic(favMusic);
                holder.btnFav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                val favMusic = FavMusic();
                favMusic.musicId = itemMusic!!.musicId;
                favMusic.artistId = itemMusic!!.artistId;
                favMusic.musicIdSort = itemMusic!!.musicIdSort;
                favMusic.musicCategory = itemMusic!!.musicCategory;
                favMusic.musicLinkMusic = itemMusic!!.musicLinkMusic;
                favMusic.musicLinkImage = itemMusic!!.musicLinkImage;
                favMusic.musicName = itemMusic!!.musicName;
                favMusic.musicTime = itemMusic!!.musicTime;
                holder.btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val image : CircleImageView = itemView.findViewById(R.id.element_id_recycler_all_music_image_music);
        val artistName : TextView = itemView.findViewById(R.id.element_id_recycler_all_music_txt_artist_name);
        val musicName : TextView = itemView.findViewById(R.id.element_id_recycler_all_music_txt_music_name);
        val btnFav : ImageView = itemView.findViewById(R.id.element_id_recycler_all_music_image_btn_fav);
    }


}
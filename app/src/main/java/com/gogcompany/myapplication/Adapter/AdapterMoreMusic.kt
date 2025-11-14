package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
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

class AdapterMoreMusic(
    private val context: Context ,
    private val list: ArrayList<Musics> ,
    private val fragmentManager: FragmentManager)
    : RecyclerView.Adapter<AdapterMoreMusic.Holder>() {

    private val ZERO = 0;
    private val ONE = 1;

    var idSort: Int? = null;
    var layoutSort: Int? = null;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMoreMusic.Holder {
        layoutSort = ZERO;
        if (idSort == 0){
            if (viewType == ZERO){
                layoutSort = R.layout.item_more_music;
            }

        }
        else{
            layoutSort = R.layout.item_more_music_two;
        }

        val viewHolder: View = LayoutInflater.from(context).inflate(layoutSort!! , parent ,  false);
        return Holder(viewHolder);
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: AdapterMoreMusic.Holder, position: Int) {
       val item: Musics = list.get(position);
        var artist: Artists = Base.dbApp.artistDao().getInfo(item.artistId);



        idSort = item.musicIdSort;
        when(holder.itemViewType){
            ZERO->{
                holder.txtMusicName.text = item.musicName;
                holder.txtMusicTime.text = item.musicTime;
                holder.txtArtistName.text = artist.artistName;
                Picasso.get().load(item.musicLinkImage).into(holder.image);
            }
            ONE->{
                holder.txtMusicName.text = item.musicName;
                holder.txtMusicTime.text = item.musicTime;
                holder.txtArtistName.text = artist.artistName;
                Picasso.get().load(item.musicLinkImage).into(holder.image);
            }
        }




        if (Base.dbApp!!.favMusicDao().isFavorite(item.musicId!!) == 1){
            holder.fav.setImageResource(R.drawable.baseline_favorite_24);
        }
        else{
            holder.fav.setImageResource(R.drawable.baseline_favorite_border_24);
        }

        holder.fav.setOnClickListener {
            if (Base.dbApp!!.favMusicDao().isFavorite(item.musicId!!) != 1){
                holder.fav.setImageResource(R.drawable.baseline_favorite_24);
                val favMusic = FavMusic();
                favMusic.musicId = item.musicId;
                favMusic.artistId = item.artistId;
                favMusic.musicIdSort = item.musicIdSort;
                favMusic.musicCategory = item.musicCategory;
                favMusic.musicLinkMusic = item.musicLinkMusic;
                favMusic.musicLinkImage = item.musicLinkImage;
                favMusic.musicName = item.musicName;
                favMusic.musicTime = item.musicTime;
                Base.dbApp!!.favMusicDao().insertFavMusic(favMusic);
                Toast.makeText(Base.activity, "add to fav" , Toast.LENGTH_SHORT)
            }
            else{
                holder.fav.setImageResource(R.drawable.baseline_favorite_border_24);
                val favMusic = FavMusic();
                favMusic.musicId = item.musicId;
                favMusic.artistId = item.artistId;
                favMusic.musicIdSort = item.musicIdSort;
                favMusic.musicCategory = item.musicCategory;
                favMusic.musicLinkMusic = item.musicLinkMusic;
                favMusic.musicLinkImage = item.musicLinkImage;
                favMusic.musicName = item.musicName;
                favMusic.musicTime = item.musicTime;
                Base.dbApp!!.favMusicDao().deleteFavMusic(favMusic);
                Toast.makeText(Base.activity, "delete form fav" , Toast.LENGTH_SHORT)
            }

            holder.item.setOnClickListener {
                val bundle = Bundle();
                bundle.putInt(keyMusicId , item.musicId!!)
                val detailMusicFragment = DetailMusicFragment();
                detailMusicFragment.arguments = bundle;
                ChangeFragment(fragmentManager).replaceFragment(detailMusicFragment , false);
            }
        }
    }

    class Holder(viewHolder : View) : RecyclerView.ViewHolder(viewHolder){
        val image: CircleImageView = viewHolder.findViewById(R.id.image_music_more);
        val txtArtistName: TextView = viewHolder.findViewById(R.id.txt_name_artist_more);
        val txtMusicName: TextView = viewHolder.findViewById(R.id.txt_name_artist_more);
        val txtMusicTime: TextView = viewHolder.findViewById(R.id.txt_time_music_more);
        val fav:ImageView = viewHolder.findViewById(R.id.btn_fav_more_music);
        val item:RelativeLayout = viewHolder.findViewById(R.id.rel_music_more);
    }
}
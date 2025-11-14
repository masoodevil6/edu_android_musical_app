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
import com.gogcompany.myapplication.App.keyArtistId
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.FavArtist.FavArtist
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.Fragment.DetailsArtistFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AdapterMoreArtist(
    private val context: Context,
    private val list: ArrayList<Artists> ,
    private val fragmentManager: FragmentManager
    ) : RecyclerView.Adapter<AdapterMoreArtist.Holder>() {

    private val ZERO = 0;
    private val ONE = 1;

    var idSort: Int? = null;
    var layoutSort: Int? = null;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMoreArtist.Holder {
        layoutSort = ZERO;
        if (idSort == 0){
            if (viewType == ZERO){
                layoutSort = R.layout.item_more_music_two
            }
        }
        else{
            layoutSort = R.layout.item_more_music
        }

        val viewHolder: View = LayoutInflater.from(context).inflate(layoutSort!! , parent ,  false);
        return Holder(viewHolder);
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    override fun onBindViewHolder(holder: AdapterMoreArtist.Holder, position: Int) {
       val item: Artists = list.get(position);

        var category = "";
        if (item.artistTypeGender == 0){
            category = "ایرانی"
        }
        else{
            category = "خارجی"
        }

        idSort = item.artistIdSort;
        when(holder.itemViewType){
            ZERO -> {
                holder.txtArtistName.text = "نام خواننده: " +item.artistName;
                holder.txtCountMusic.text = "تعداد موزیک: " + item.artistCountMusic.toString();
                holder.txtTimeMusic.text = category;
                Picasso.get().load(item.artistLinkImage).into(holder.image)
            }
            ONE -> {
                holder.txtArtistName.text = "نام خواننده: " +item.artistName;
                holder.txtCountMusic.text = "تعداد موزیک: " + item.artistCountMusic.toString();
                holder.txtTimeMusic.text = category;
                Picasso.get().load(item.artistLinkImage).into(holder.image)
            }
        }


        holder.itemView.setOnClickListener{
            val bundle = Bundle();
            bundle.putInt(keyArtistId , item.artistId!!)
            var fragmentArtist = DetailsArtistFragment();
            fragmentArtist.arguments= bundle;
            ChangeFragment(fragmentManager).replaceFragment(fragmentArtist , false)
        }

        if (Base.dbApp!!.favArtistDao().isFavorite(item.artistId!!) == 1){
            holder.fav.setImageResource(R.drawable.baseline_favorite_24)
        }
        else{
            holder.fav.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        holder.fav.setOnClickListener{
            if (Base.dbApp!!.favArtistDao().isFavorite(item.artistId!!) != 1){
                var favArtist = FavArtist();
                favArtist.artistId = item.artistId;
                favArtist.artistIdSort = item.artistIdSort;
                favArtist.artistName = item.artistName;
                favArtist.artistCountMusic = item.artistCountMusic;
                favArtist.artistLinkImage = item.artistLinkImage;
                favArtist.artistTypeGender = item.artistTypeGender;
                Base.dbApp!!.favArtistDao().insertArtistFav(favArtist);
                holder.fav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                var favArtist = FavArtist();
                favArtist.artistId = item.artistId;
                favArtist.artistIdSort = item.artistIdSort;
                favArtist.artistName = item.artistName;
                favArtist.artistCountMusic = item.artistCountMusic;
                favArtist.artistLinkImage = item.artistLinkImage;
                favArtist.artistTypeGender = item.artistTypeGender;
                Base.dbApp!!.favArtistDao().deleteArtistFav(favArtist);
                holder.fav.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

    }

    class Holder(viewHolder : View) : RecyclerView.ViewHolder(viewHolder){
        val image: CircleImageView = viewHolder.findViewById(R.id.image_music_more);
        val txtArtistName: TextView = viewHolder.findViewById(R.id.txt_name_artist_more);
        val txtCountMusic: TextView = viewHolder.findViewById(R.id.txt_name_music_more);
        val txtTimeMusic: TextView = viewHolder.findViewById(R.id.txt_time_music_more);
        val fav : ImageView = viewHolder.findViewById(R.id.btn_fav_more_music)

    }
}
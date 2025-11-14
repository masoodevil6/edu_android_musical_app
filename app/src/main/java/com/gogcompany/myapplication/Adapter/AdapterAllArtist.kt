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
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.FavArtist.FavArtist
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AdapterAllArtist(
    private val context: Context ,
    private val list : ArrayList<Artists> ,
    private val fragmentManager: FragmentManager
): RecyclerView.Adapter<AdapterAllArtist.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAllArtist.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_all_artist , parent , false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterAllArtist.Holder, position: Int) {
        val itemArtist = list.get(position);
        
        holder.artistName.text = itemArtist.artistName;
        holder.musicCount.text = itemArtist.artistCountMusic.toString();
        Picasso.get().load(itemArtist.artistLinkImage).into(holder.image)


        if (Base.dbApp!!.favArtistDao().isFavorite(itemArtist.artistId!!) == 1){
            holder.btnFav.setImageResource(R.drawable.baseline_favorite_24)
        }
        else{
            holder.btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        holder.btnFav.setOnClickListener{
            if (Base.dbApp!!.favArtistDao().isFavorite(itemArtist.artistId!!) != 1){
                var favArtist = FavArtist();
                favArtist.artistId = itemArtist.artistId;
                favArtist.artistIdSort = itemArtist.artistIdSort;
                favArtist.artistName = itemArtist.artistName;
                favArtist.artistCountMusic = itemArtist.artistCountMusic;
                favArtist.artistLinkImage = itemArtist.artistLinkImage;
                favArtist.artistTypeGender = itemArtist.artistTypeGender;
                Base.dbApp!!.favArtistDao().insertArtistFav(favArtist);
                holder.btnFav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                var favArtist = FavArtist();
                favArtist.artistId = itemArtist.artistId;
                favArtist.artistIdSort = itemArtist.artistIdSort;
                favArtist.artistName = itemArtist.artistName;
                favArtist.artistCountMusic = itemArtist.artistCountMusic;
                favArtist.artistLinkImage = itemArtist.artistLinkImage;
                favArtist.artistTypeGender = itemArtist.artistTypeGender;
                Base.dbApp!!.favArtistDao().deleteArtistFav(favArtist);
                holder.btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }

        holder.itemView.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

    class Holder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val image : CircleImageView = itemView.findViewById(R.id.element_id_recycler_all_artist_image_artist);
        val artistName : TextView = itemView.findViewById(R.id.element_id_recycler_all_artist_txt_artist_name);
        val musicCount : TextView = itemView.findViewById(R.id.element_id_recycler_all_artist_txt_music_num);
        val btnFav : ImageView = itemView.findViewById(R.id.element_id_fragment_all_artist_image_btn_fav);

    }
}
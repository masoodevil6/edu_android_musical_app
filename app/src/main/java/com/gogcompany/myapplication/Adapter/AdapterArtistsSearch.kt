package com.gogcompany.myapplication.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.keyArtistId
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.Fragment.DetailsArtistFragment
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AdapterArtistsSearch(
    private val context:            Context,
    private val List:               ArrayList<Artists> ,
    private val fragmentManager:    FragmentManager
    ): RecyclerView.Adapter<AdapterArtistsSearch.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterArtistsSearch.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_artist_search , parent, false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterArtistsSearch.Holder, position: Int) {
        val artists = List[position];
        holder.textName.text = artists.artistName;
        Picasso.get().load(artists.artistLinkImage).into(holder.image);

        holder.itemView.setOnClickListener{
            val bundle = Bundle();
            bundle.putInt(keyArtistId , artists.artistId!!)
            var fragmentArtist = DetailsArtistFragment();
            fragmentArtist.arguments= bundle;
            ChangeFragment(fragmentManager).replaceFragment(fragmentArtist , false)
        }
    }

    override fun getItemCount(): Int {
        return List.size;
    }


    class Holder(itemView:View):RecyclerView.ViewHolder(itemView){

        val textName: TextView     = itemView.findViewById(R.id.element_id_recycler_artist_search_txt_name_singer);
        val image: CircleImageView = itemView.findViewById(R.id.element_id_recycler_artist_search_image_singer);



    }
}
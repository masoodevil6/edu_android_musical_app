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

class AdapterArtists(
    private val context:            Context,
    private val List:               ArrayList<Artists> ,
    private val fragmentManager:    FragmentManager
    ): RecyclerView.Adapter<AdapterArtists.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterArtists.Holder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_artist , parent, false);
        return Holder(layout);
    }

    override fun onBindViewHolder(holder: AdapterArtists.Holder, position: Int) {
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

        val textName: TextView     = itemView.findViewById(R.id.txt_name_artist_item);
        val image: CircleImageView = itemView.findViewById(R.id.image_artist_item);



    }
}
package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterItemMusic
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.keyArtistId
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.FavArtist.FavArtist
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class DetailsArtistFragment(): Fragment() {

    lateinit var layout: View;

    lateinit var imageFull:          ImageView;
    lateinit var circleImageView:    CircleImageView;
    lateinit var txtNameArtist:      TextView;
    lateinit var txtCountMusic:      TextView;
    lateinit var txtCategoryArtist:  TextView;
    lateinit var btnBack:            ImageView;
    lateinit var btnFav:             ImageView;
    lateinit var recyclerViewMusics: RecyclerView;

    var artistId: Int? = null;
    var artistInfo: Artists? = null;
    var artistMusics = ArrayList<Musics>();
    lateinit var adapterItemMusic:AdapterItemMusic;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_details_artist , container , false)
        castView();
        getDataBundle();
        getData();
        setView();
        return layout
    }

    private fun castView(){
        imageFull           = layout.findViewById(R.id.img_full_artist);
        circleImageView     = layout.findViewById(R.id.image_circle_artist);
        txtNameArtist       = layout.findViewById(R.id.txt_name_artist_detail);
        txtCountMusic       = layout.findViewById(R.id.txt_count_music_detail_artist);
        txtCategoryArtist   = layout.findViewById(R.id.txt_category_detail_artist);
        btnBack             = layout.findViewById(R.id.back_detail_artist);
        btnFav              = layout.findViewById(R.id.favorite_detail_artist);
        recyclerViewMusics  = layout.findViewById(R.id.rec_music_details_artist);
    }

    private fun getDataBundle(){
        artistId = arguments?.getInt(keyArtistId , 0)
    }

    private fun getData() {
        artistInfo = Base.dbApp.artistDao().getInfo(artistId!!)

    }

    private fun setView() {
        if (artistInfo != null){
            Picasso.get().load(artistInfo?.artistLinkImage).into(imageFull);
            Picasso.get().load(artistInfo?.artistLinkImage).into(circleImageView);
            txtNameArtist.text =  "نام خواننده: ${artistInfo?.artistName}" ;
            txtCountMusic.text =  "تعداد موزیک ها: ${artistInfo?.artistCountMusic}" ;
            txtCategoryArtist.text = if (artistInfo?.artistTypeGender==0) "ایرانی" else "خارجی" ;

            recyclerViewMusics.setHasFixedSize(true);
            recyclerViewMusics.layoutManager = LinearLayoutManager(Base.activity);
            Base.dbApp!!.musicDao().getItemMusicFromArtistId(artistId!!).observe(requireActivity() , {
                    musics->
                artistMusics.clear();
                artistMusics.addAll(musics);
                adapterItemMusic.notifyDataSetChanged();
            })
            adapterItemMusic = AdapterItemMusic(Base.activity , artistMusics , requireActivity().supportFragmentManager);
            recyclerViewMusics.adapter = adapterItemMusic;


            if (Base.dbApp!!.favArtistDao().isFavorite(artistId!!) == 1){
                btnFav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
            }

            btnFav.setOnClickListener {
                if (Base.dbApp!!.favArtistDao().isFavorite(artistId!!) != 1){
                    var favArtist = FavArtist();
                    favArtist.artistId = artistInfo!!.artistId;
                    favArtist.artistIdSort = artistInfo!!.artistIdSort;
                    favArtist.artistName = artistInfo!!.artistName;
                    favArtist.artistCountMusic = artistInfo!!.artistCountMusic;
                    favArtist.artistLinkImage = artistInfo!!.artistLinkImage;
                    favArtist.artistTypeGender = artistInfo!!.artistTypeGender;
                    Base.dbApp!!.favArtistDao().insertArtistFav(favArtist);
                    btnFav.setImageResource(R.drawable.baseline_favorite_24)
                }
                else{
                    var favArtist = FavArtist();
                    favArtist.artistId = artistInfo!!.artistId;
                    favArtist.artistIdSort = artistInfo!!.artistIdSort;
                    favArtist.artistName = artistInfo!!.artistName;
                    favArtist.artistCountMusic = artistInfo!!.artistCountMusic;
                    favArtist.artistLinkImage = artistInfo!!.artistLinkImage;
                    favArtist.artistTypeGender = artistInfo!!.artistTypeGender;
                    Base.dbApp!!.favArtistDao().deleteArtistFav(favArtist);
                    btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }
        }
    }

}
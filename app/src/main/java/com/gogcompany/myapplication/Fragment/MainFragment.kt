package com.gogcompany.myapplication.Fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterArtists
import com.gogcompany.myapplication.Adapter.AdapterMusics
import com.gogcompany.myapplication.Adapter.AdapterVideo
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.KeyId
import com.gogcompany.myapplication.App.KeyMore
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.R

class MainFragment(): Fragment() {

    lateinit var layout:View;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_main, container, false)
        cast()
        return layout // ← به جای super.onCreateView
    }


    lateinit var moreIri: RelativeLayout;
    lateinit var recyclerIri:RecyclerView;
    var listAristsIri: ArrayList<Artists> = ArrayList();
    lateinit var adapterArtistsIri: AdapterArtists;

    lateinit var moreFor: RelativeLayout;
    lateinit var recyclerFor:RecyclerView;
    var listAristsFor: ArrayList<Artists> = ArrayList();
    lateinit var adapterArtistsFor: AdapterArtists;

    lateinit var moreNew: RelativeLayout;
    lateinit var adapterMusicsNew:AdapterMusics;
    lateinit var recMusicNew: RecyclerView;
    var listMusicNew = ArrayList<Musics>();

    lateinit var morePop: RelativeLayout;
    lateinit var adapterMusicsPop:AdapterMusics;
    lateinit var recMusicPop: RecyclerView;
    var listMusicPop = ArrayList<Musics>();

    lateinit var moreVideo: RelativeLayout;
    lateinit var recyclerVideo: RecyclerView;
    lateinit var adapterVideo: AdapterVideo;
    var listVideo = ArrayList<Video>();

    lateinit var btnMenu: ImageView;
    lateinit var drawerLayout: DrawerLayout;

    lateinit var btnSearch: LinearLayout
    lateinit var btnMenuFav: LinearLayout
    lateinit var btnAllMusics: LinearLayout
    lateinit var btnAllArtist: LinearLayout
    lateinit var btmVideoApp: LinearLayout
    lateinit var btnAddMusic: LinearLayout

    private fun cast(){

        btnMenu = layout.findViewById(R.id.btn_menu);
        drawerLayout = layout.findViewById(R.id.drawer_layout);

        btnMenu.setOnClickListener {
            if (drawerLayout.isDrawerVisible(GravityCompat.END)){
                drawerLayout.closeDrawer(GravityCompat.END);
            }
            else{
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }

        getArtistIranian();
        getArtistForigen();
        getMusicNew();
        getMusicPop();
        getVideo();



        val argMoreIri : Map<String , Any> = mapOf(
            KeyMore to 0,
            KeyId to 0
        )
        setFragment(MoreFragment() ,moreIri, argMoreIri);

        val argMoreFor: Map<String , Any> = mapOf(
            KeyMore to 1,
            KeyId to 0
        )
        setFragment(MoreFragment() ,moreFor, argMoreFor);



        val argMoreNew: Map<String , Any> = mapOf(
            KeyMore to 0,
            KeyId to 1
        )
        setFragment(MoreFragment() ,moreNew, argMoreNew);

        val argMorePop: Map<String , Any> = mapOf(
            KeyMore to 1,
            KeyId to 1
        )
        setFragment(MoreFragment() ,morePop, argMorePop);



        val argVideo: Map<String , Any> = mapOf(

        )
        setFragment(VideoAllFragment() , moreVideo, argVideo);



        btnSearch = layout.findViewById(R.id.element_id_layout_menu_linear_item_search)
        btnSearch.setOnClickListener {
            goToFragment(SearchFragment() )
        }

        btnMenuFav = layout.findViewById(R.id.element_id_layout_menu_linear_item_favorite)
        btnMenuFav.setOnClickListener {
            goToFragment(FavoriteFragment() )
        }

        btnAllMusics = layout.findViewById(R.id.element_id_layout_menu_linear_item_all_music)
        btnAllMusics.setOnClickListener {
            goToFragment(MusicsAllFragment() )
        }

        btnAllArtist = layout.findViewById(R.id.element_id_layout_menu_linear_all_artist)
        btnAllArtist.setOnClickListener {
            goToFragment(ArtistAllFragment() )
        }

        btnAllArtist = layout.findViewById(R.id.element_id_layout_menu_linear_item_all_video)
        btnAllArtist.setOnClickListener {
            goToFragment(VideoAllFragment() )
        }

        btmVideoApp = layout.findViewById(R.id.element_id_layout_menu_linear_item_my_films)
        btmVideoApp.setOnClickListener {
            goToFragment(VideoAppFragment() )
        }

        btnAddMusic = layout.findViewById(R.id.element_id_layout_menu_linear_item_add_music)
        btnAddMusic.setOnClickListener {
            goToFragment(AddFragment() )
        }
    }

    private fun goToFragment(fragment: Fragment){
        ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(fragment , true);
        drawerLayout.closeDrawer(GravityCompat.END);
    }


    private fun setFragment(fragment: Fragment , view: View,  args: Map<String , Any>){
        view.setOnClickListener{
            fragment.arguments= setFragmentBundle(args);
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(fragment , true);
        }
    }

    private fun setFragmentBundle(args: Map<String , Any>) : Bundle{
        val bundle = Bundle();
        for ((key , value) in args){
            when(value){
                is String -> bundle.putString(key , value as String)
                is Int    -> bundle.putInt(key    , value as Int)
            }
        }
        return bundle;
    }

    private fun getArtistIranian(){
        moreIri = layout.findViewById(R.id.rel_more_artist_iri);
        recyclerIri = layout.findViewById(R.id.recycler_main_music_artist_iranian);
        recyclerIri.setHasFixedSize(true);
        recyclerIri.layoutManager = LinearLayoutManager(Base.activity, LinearLayoutManager.HORIZONTAL , false);

        Base.dbApp.artistDao().getCategory(0).observe(
            requireActivity() ,
            {artist->
                listAristsIri.clear();
                listAristsIri.addAll(artist);
                adapterArtistsIri.notifyDataSetChanged();
            })

        adapterArtistsIri= AdapterArtists(Base.activity, listAristsIri , requireActivity().supportFragmentManager);
        recyclerIri.adapter=adapterArtistsIri;
    }

    private fun getArtistForigen(){
        moreFor = layout.findViewById(R.id.rel_more_artist_for);
        recyclerFor = layout.findViewById(R.id.recycler_main_music_artist_forign);
        recyclerFor.setHasFixedSize(true);
        recyclerFor.layoutManager = LinearLayoutManager(Base.activity, LinearLayoutManager.HORIZONTAL , false);

        Base.dbApp.artistDao().getCategory(1).observe(
            requireActivity() ,
            {artist->
                listAristsFor.clear();
                listAristsFor.addAll(artist);
                adapterArtistsIri.notifyDataSetChanged();
            })

        adapterArtistsFor= AdapterArtists(Base.activity, listAristsFor , requireActivity().supportFragmentManager);
        recyclerFor.adapter=adapterArtistsFor;
    }

    private fun getMusicNew(){
        moreNew = layout.findViewById(R.id.rel_more_music_new);
        recMusicNew = layout.findViewById(R.id.recycler_main_music_new);
        recMusicNew.setHasFixedSize(true);
        recMusicNew.layoutManager = LinearLayoutManager(Base.activity , LinearLayoutManager.HORIZONTAL , false);

        Base.dbApp.musicDao().getCategoryMusic(0).observe(requireActivity() , {
            music->
            listMusicNew.clear();
            listMusicNew.addAll(music);
            adapterMusicsNew.notifyDataSetChanged();
        })

        adapterMusicsNew = AdapterMusics(Base.activity , listMusicNew , requireActivity().supportFragmentManager);
        recMusicNew.adapter = adapterMusicsNew;
    }


    private fun getMusicPop(){
        morePop = layout.findViewById(R.id.rel_more_music_pop);

        recMusicPop = layout.findViewById(R.id.recycler_main_music_pop);
        recMusicPop.setHasFixedSize(true);
        recMusicPop.layoutManager = LinearLayoutManager(Base.activity , LinearLayoutManager.HORIZONTAL , false);

        Base.dbApp.musicDao().getCategoryMusic(1).observe(requireActivity() , {
            music->
            listMusicPop.clear();
            listMusicPop.addAll(music);
            adapterMusicsPop.notifyDataSetChanged();
        })

        adapterMusicsPop = AdapterMusics(Base.activity , listMusicPop , requireActivity().supportFragmentManager);
        recMusicPop.adapter = adapterMusicsPop;
    }



    private fun getVideo(){
        moreVideo = layout.findViewById(R.id.rel_more_video);

        recyclerVideo = layout.findViewById(R.id.recycler_main_video);
        recyclerVideo.setHasFixedSize(true);
        recyclerVideo.layoutManager = LinearLayoutManager(Base.activity , LinearLayoutManager.VERTICAL , false);

        Base.dbApp.VideoDao().getLastVideo().observe(requireActivity() , {
                video->
            listVideo.clear();
            listVideo.addAll(video);
            adapterVideo.notifyDataSetChanged();
        })

        adapterVideo = AdapterVideo(Base.activity , listVideo , requireActivity().supportFragmentManager);
        recyclerVideo.adapter = adapterVideo;
    }

}
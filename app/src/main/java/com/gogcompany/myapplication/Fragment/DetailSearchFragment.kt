package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterArtistsSearch
import com.gogcompany.myapplication.Adapter.AdapterMusicAddSearch
import com.gogcompany.myapplication.Adapter.AdapterMusicsSearch
import com.gogcompany.myapplication.Adapter.AdapterVideoSearch
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAdd
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.R

class DetailSearchFragment() : Fragment() {

    lateinit var layout:             View;
    lateinit var txtToolBar :        TextView;
    lateinit var editTextSearch :    EditText;
    lateinit var btnBack :           ImageView;
    lateinit var recListSearched :   RecyclerView;

    var ListArtist = ArrayList<Artists>()
    lateinit var adapterArtistsSearch: AdapterArtistsSearch;

    var ListMusic = ArrayList<Musics>()
    lateinit var adapterMusicsSearch: AdapterMusicsSearch;

    var ListVideo = ArrayList<Video>()
    lateinit var adapterVideoSearch: AdapterVideoSearch;

    var listMusicAdd = ArrayList<MusicAdd>()
    lateinit var adapterMusicAddSearch: AdapterMusicAddSearch;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_details_search , container , false);
        cast();
        setViews();
        return layout;
    }

    private fun cast() {
        btnBack =            layout.findViewById(R.id.element_id_favorite_details_search_image_btn_back);
        txtToolBar =         layout.findViewById(R.id.element_id_favorite_details_search_text_tool_bar);
        editTextSearch =     layout.findViewById(R.id.element_id_favorite_details_search_edit_text_search);
        recListSearched =    layout.findViewById(R.id.element_id_favorite_details_search_rec_list_searched);
    }

    private fun setViews() {
        recListSearched.setHasFixedSize(true);
        recListSearched.layoutManager = GridLayoutManager(Base.activity , 2);


        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_SINGER_IRAN){
            txtToolBar.text = "جسنجوی خواننده ایرانی"
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_SINGER_FOREGIN){
            txtToolBar.text = "جسنجوی خواننده خارجی"
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_MUSIC_ADD){
            txtToolBar.text = "جسنجوی موزیک اضافه شده"
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_MUSIC){
            txtToolBar.text = "جسنجوی موزیک "
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_VIDEO){
            txtToolBar.text = "جسنجوی موزیک ویدئو "
        }



        editTextSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(textSerch: Editable?) {
                search(textSerch.toString());
            }
        })


        btnBack.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(SearchFragment() , false);
            }
        })
    }

    private fun search(textSearch: String){
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_SINGER_IRAN){
            Base.dbApp!!.artistDao().searchArtist(0 , textSearch).observe(
                requireActivity(),
                { artists->
                    ListArtist.clear();
                    ListArtist.addAll(artists);
                    adapterArtistsSearch.notifyDataSetChanged();
                }
            );
            adapterArtistsSearch = AdapterArtistsSearch(Base.activity , ListArtist , requireActivity().supportFragmentManager);
            recListSearched.adapter = adapterArtistsSearch;
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_SINGER_FOREGIN){
            Base.dbApp!!.artistDao().searchArtist(1 , textSearch).observe(
                requireActivity(),
                { artists->
                    ListArtist.clear();
                    ListArtist.addAll(artists);
                    adapterArtistsSearch.notifyDataSetChanged();
                }
            );
            adapterArtistsSearch = AdapterArtistsSearch(Base.activity , ListArtist , requireActivity().supportFragmentManager);
            recListSearched.adapter = adapterArtistsSearch;
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_MUSIC_ADD){

        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_MUSIC){
            Base.dbApp!!.musicDao().searchMusic( textSearch).observe(
                requireActivity(),
                { musics->
                    ListMusic.clear();
                    ListMusic.addAll(musics);
                    adapterMusicsSearch.notifyDataSetChanged();
                }
            );
            adapterMusicsSearch = AdapterMusicsSearch(Base.activity , ListMusic , requireActivity().supportFragmentManager);
            recListSearched.adapter = adapterMusicsSearch;
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_VIDEO){

            Base.dbApp!!.VideoDao().searchVideo(textSearch).observe(
                requireActivity() ,
                {
                    videos->
                    ListVideo.clear();
                    ListVideo.addAll(videos)
                    adapterVideoSearch.notifyDataSetChanged();
                }
            )
            adapterVideoSearch = AdapterVideoSearch(Base.activity , ListVideo , requireActivity().supportFragmentManager);
            recListSearched.adapter = adapterVideoSearch;
        }
        if (SearchFragment._fragmentType == SearchFragment._FRAGMENT_TYPE_MUSIC_ADD){

            Base.dbApp!!.musicAddDao().searchMusicAdd(textSearch).observe(
                requireActivity() ,
                {
                    musicAdds->
                    listMusicAdd.clear();
                    listMusicAdd.addAll(musicAdds)
                    adapterMusicAddSearch.notifyDataSetChanged();
                }
            )
            adapterMusicAddSearch = AdapterMusicAddSearch(Base.activity , listMusicAdd , requireActivity().supportFragmentManager);
            recListSearched.adapter = adapterMusicAddSearch;
        }
    }

}
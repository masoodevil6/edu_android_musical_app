package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gogcompany.myapplication.Adapter.AdapterAllArtist
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.R

class ArtistAllFragment() : Fragment() {

    lateinit var layout: View;

    lateinit var btnBack: ImageView;
    lateinit var recListArtist: RecyclerView;

    var list = ArrayList<Artists>();
    lateinit var adapter : AdapterAllArtist;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_artist_all , container , false);
        cast();
        setViews();
        return layout;
    }

    private fun cast() {
        btnBack = layout.findViewById(R.id.element_id_fragment_all_artist_image_btn_back)
        recListArtist = layout.findViewById(R.id.element_id_fragment_all_artist_recycler_view_list_artist)
    }

    private fun setViews() {
        btnBack.setOnClickListener {
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , false)
        }

        recListArtist.setHasFixedSize(true);
        recListArtist.layoutManager = LinearLayoutManager(Base.activity)

        Base.dbApp!!.artistDao().getAll().observe(requireActivity() , {
            artists ->
            list.clear();
            list.addAll(artists);
            adapter.notifyDataSetChanged();
        })

        adapter = AdapterAllArtist(Base.activity , list , requireActivity().supportFragmentManager)
        recListArtist.adapter = adapter
    }


}
package com.gogcompany.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAdd
import com.gogcompany.myapplication.R

class AddMusicFragment() : Fragment() {

    lateinit var layout:View;
    lateinit var editArtistName : EditText;
    lateinit var editMusicName : EditText;
    lateinit var editMusicPath : EditText;
    lateinit var btnSubmit : RelativeLayout;



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_add_music , container , false);
        cast();
        setViews();
        return layout;
    }


    private fun cast() {
        editArtistName = layout.findViewById(R.id.element_id_fragment_add_music_edit_artist_name)
        editMusicName = layout.findViewById(R.id.element_id_fragment_add_music_edit_music_name)
        editMusicPath = layout.findViewById(R.id.element_id_fragment_add_music_edit_music_url)
        btnSubmit = layout.findViewById(R.id.element_id_fragment_add_music_relative_btn_submit)
    }

    private fun setViews() {

        btnSubmit.setOnClickListener {
            var artisName = editArtistName.text.toString();
            var musicName = editMusicName.text.toString();
            var musicUrl = editMusicPath.text.toString();
            addmusic.onAddMusic(artisName , musicName , musicUrl)

//            val musicAdd = MusicAdd();
//            musicAdd.artistName = editArtistName.text.toString();
//            musicAdd.musicName = editMusicName.text.toString();
//            musicAdd.musicUrl = editMusicPath.text.toString();
            //Base.dbApp!!.musicAddDao().insertMusic(musicAdd)

            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(AddFragment() , false);
        }
    }


    companion object{
        lateinit var addmusic: IAddMusic;
        fun addMusicFun(iAddMusic: IAddMusic){
            addmusic = iAddMusic;
        }
    }

    interface IAddMusic{
        fun onAddMusic(artistName:String , musicName:String , musicUrl:String)
    }


}
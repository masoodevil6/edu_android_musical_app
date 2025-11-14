package com.gogcompany.myapplication.Fragment

import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSeekBar
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.Utilities
import com.gogcompany.myapplication.App.keyMusicId
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusic
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAdd
import com.gogcompany.myapplication.DataBase.MusicAddFav.MusicAddFav
import com.gogcompany.myapplication.R
import com.squareup.picasso.Picasso
import com.triggertrap.seekarc.SeekArc
import java.io.IOException

class DetailMusicAddFragment() :Fragment(){

    lateinit var layout: View;

    lateinit var btnDownload:       RelativeLayout;
    lateinit var btnBack:           RelativeLayout;
    lateinit var btnFav:            RelativeLayout;
    lateinit var btnFavImage:       ImageView;
    lateinit var textMusicName:     TextView;
    lateinit var textArtistName:    TextView;



    var musicId: Int? = 0;




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_details_music_add , container , false);
        cast();
        getParams();
        getResultDb();
        setViews()
        return layout;
    }



    private fun cast(){
        btnDownload =         layout.findViewById(R.id.element_id_fragment_detail_music_btn_download_music_add);
        btnBack =             layout.findViewById(R.id.element_id_fragment_detail_music_btn_back_music_add);
        btnFav =              layout.findViewById(R.id.element_id_fragment_detail_music_btn_fav_music_add);
        btnFavImage =         layout.findViewById(R.id.element_id_fragment_detail_music_image_fav_music_add);
        textMusicName =       layout.findViewById(R.id.element_id_fragment_detail_music_text_music_name_add);
        textArtistName =      layout.findViewById(R.id.element_id_fragment_detail_music_text_artist_name_add);
        textDurationTime =    layout.findViewById(R.id.element_id_fragment_detail_music_text_music_time_duration_add);
        textPlayTime =        layout.findViewById(R.id.element_id_fragment_detail_music_text_music_time_play_add);
        btnForward =          layout.findViewById(R.id.element_id_fragment_detail_music_btn_forward_music_add);
        btnReplay =           layout.findViewById(R.id.element_id_fragment_detail_music_btn_replay_music_add);
        btnPlay =             layout.findViewById(R.id.element_id_fragment_detail_music_btn_play_music_add);
        seekBar =             layout.findViewById(R.id.element_id_fragment_detail_music_seek_arc_add);

        mediaPlayer = MediaPlayer();
        utilities = Utilities();
        handler = Handler();
    }

    private fun getParams(){
        musicId = requireArguments().getInt(keyMusicId);
    }

    private fun getResultDb(){
        musicData = Base.dbApp!!.musicAddDao().getInfoMusic(musicId);
    }

    private fun setViews(){
        if (musicData != null && artistData != null){
            textMusicName.text = musicData!!.musicName;
            textArtistName.text = artistData!!.artistName;

            btnPlay.isEnabled= false;
            btnForward.isEnabled= false;
            btnReplay.isEnabled= false;


            if (Base.dbApp!!.musicAddFavDao().isFav(musicId!!) == 1){
                btnFavImage.setImageResource(R.drawable.baseline_favorite_24);
            }
            else{
                btnFavImage.setImageResource(R.drawable.baseline_favorite_border_24);
            }

            btnFav.setOnClickListener{
                if (Base.dbApp!!.musicAddFavDao().isFav(musicId!!) != 1){
                    val musicAddFav = MusicAddFav();
                    musicAddFav.id = musicData!!.id;
                    musicAddFav.musicName = musicData!!.musicName;
                    musicAddFav.musicUrl = musicData!!.musicUrl;
                    musicAddFav.artistName = musicData!!.artistName;
                    Base.dbApp!!.musicAddFavDao().insertMusicAddFav(musicAddFav);
                    btnFavImage.setImageResource(R.drawable.baseline_favorite_24);
                }
                else{
                    val musicAddFav = MusicAddFav();
                    musicAddFav.id = musicData!!.id;
                    musicAddFav.musicName = musicData!!.musicName;
                    musicAddFav.musicUrl = musicData!!.musicUrl;
                    musicAddFav.artistName = musicData!!.artistName;
                    Base.dbApp!!.musicAddFavDao().deleteMusicAddFav(musicAddFav);
                    btnFavImage.setImageResource(R.drawable.baseline_favorite_border_24);
                }
            }

            btnPlay.setOnClickListener{
                if (mediaPlayer.isPlaying){
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.baseline_play_arrow_24);
                }
                else{
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.baseline_pause_24);
                }
            }

            btnForward.setOnClickListener {
                val totalTime = mediaPlayer.duration.toLong();
                val currentTime = mediaPlayer.currentPosition.toLong();

                if (currentTime+5000 <= totalTime){
                    mediaPlayer.seekTo((currentTime+5000).toInt())
                }
                else{
                    mediaPlayer.seekTo(totalTime.toInt())
                }
            }

            btnReplay.setOnClickListener {
                val currentTime = mediaPlayer.currentPosition;
                if (currentTime-5000 >= 0){
                    mediaPlayer.seekTo(currentTime-5000)
                }
                else{
                    mediaPlayer.seekTo(0)
                }
            }

            seekBar.setOnSeekArcChangeListener(object:SeekArc.OnSeekArcChangeListener{
                override fun onProgressChanged(p0: SeekArc?, p1: Int, p2: Boolean) {

                }

                override fun onStartTrackingTouch(p0: SeekArc?) {
                    val total = mediaPlayer.duration;
                    val currentPosition = utilities.progressToTimer(p0!!.progress , total);

                    mediaPlayer.seekTo(currentPosition)
                    updateTime();
                }

                override fun onStopTrackingTouch(p0: SeekArc?) {

                }

            })




            if (!mediaPlayer.isPlaying){
                val player = Player();
                player.execute();
            }
        }
    }





    companion object{
        lateinit var btnForward:        ImageView;
        lateinit var btnReplay:         ImageView;
        lateinit var btnPlay:           ImageView;
        lateinit var textDurationTime:  TextView;
        lateinit var textPlayTime:      TextView;
        lateinit var seekBar:           SeekArc;

        lateinit var handler: Handler;
        lateinit var utilities: Utilities;

        lateinit var mediaPlayer:MediaPlayer;
        var musicData: MusicAdd? = null;
        var artistData: Artists? = null;

        class Player(): AsyncTask<Any , Any , Any>(){
            override fun doInBackground(objects: Array<Any?>): Any? {
                try {
                    mediaPlayer.setDataSource("" + musicData!!.musicUrl)
                    mediaPlayer.prepare();
                }
                catch (ioException: IOException){
                    ioException.printStackTrace();
                }
                return null;
            }


            override fun onPostExecute(result: Any?) {
                super.onPostExecute(result)
                mediaPlayer.start();
                btnPlay.isEnabled= true;
                btnForward.isEnabled= true;
                btnReplay.isEnabled= true;
                btnPlay.setImageResource(R.drawable.baseline_pause_24);
                updateTime();
            }

        }


        private fun updateTime(){
            val totalTime = mediaPlayer.duration.toLong();
            val currentTime = mediaPlayer.currentPosition.toLong();

            textPlayTime.text = "" + utilities.milliSecondsToTimer(currentTime);
            textDurationTime.text = "" + utilities.milliSecondsToTimer(totalTime);

            val runnable = Runnable {updateTime()};
            handler.postDelayed(runnable , 1000);

            val progress = utilities.getProgressPercentage(currentTime , totalTime);
            seekBar.progress = progress;
        }
    }

}
package com.gogcompany.myapplication.Fragment

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.TextView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.gogcompany.myapplication.App.Base
import com.gogcompany.myapplication.App.ChangeFragment
import com.gogcompany.myapplication.App.Utilities
import com.gogcompany.myapplication.App.keyVideoId
import com.gogcompany.myapplication.DataBase.FavVideo.FavVideo
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.R

class DetailVideoFragment() : Fragment() {

    lateinit var layout:         View;

    lateinit var btnBack:        ImageView;
    lateinit var btnDownload:    ImageView;
    lateinit var btnFav:         ImageView;
    lateinit var pageTitle:      TextView;
    lateinit var relVideo:       RelativeLayout;
    lateinit var relLayoutVideo: RelativeLayout;

    var isOpen = true;
    var videoData: Video? = null;
    var videoId: Int? = null;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = inflater.inflate(R.layout.fragment_details_video , container , false)
        cast();
        getParams();
        setViews();
        return layout;
    }


    private fun cast() {
        btnBack =          layout.findViewById(R.id.element_id_layout_video_player_image_btn_back)
        btnDownload =      layout.findViewById(R.id.element_id_layout_video_player_image_brn_download)
        btnFav =           layout.findViewById(R.id.element_id_layout_video_player_image_brn_fav)
        btnPlay =          layout.findViewById(R.id.element_id_layout_video_player_image_brn_play)
        btnRePlay =        layout.findViewById(R.id.element_id_layout_video_player_image_brn_replay)
        btnForward =       layout.findViewById(R.id.element_id_layout_video_player_image_brn_forward)
        timeStart =        layout.findViewById(R.id.element_id_layout_video_player_txt_time_start)
        timeEnd =          layout.findViewById(R.id.element_id_layout_video_player_txt_time_end)
        seekbar =          layout.findViewById(R.id.element_id_layout_video_player_seek_bar_player)
        videoPlayer =      layout.findViewById(R.id.element_id_fragment_video_video_player)
        pageTitle =        layout.findViewById(R.id.element_id_layout_video_player_txt_video_name)
        relVideo =         layout.findViewById(R.id.element_id_fragment_video_rel_parent)
        relLayoutVideo =   layout.findViewById(R.id.element_id_layout_video_player_relative_parent)
    }

    private fun getParams() {
        videoId = arguments?.getInt(keyVideoId);
        if (videoId != null){
            videoData = Base.dbApp!!.VideoDao().getInfoVideo(videoId!!);
        }
    }

    private fun setViews() {

        pageTitle.text = videoData!!.videoName;

        btnBack.setOnClickListener {
            ChangeFragment(requireActivity().supportFragmentManager).replaceFragment(MainFragment() , false);
        }

        videoPlayer.setVideoURI(Uri.parse(videoData!!.videoLinkVideo));

        utilities = Utilities();
        handler = Handler();

        btnPlay.isEnabled = false;
        btnPlay.setOnClickListener {
            if (videoPlayer.isPlaying){
                btnPlay.setImageResource(R.drawable.baseline_play_arrow_24)
                videoPlayer.pause();
            }
            else{
                btnPlay.setImageResource(R.drawable.baseline_pause_24)
                videoPlayer.start();
                onUpdate();
            }
        }

        btnForward.isEnabled = false;
        btnForward.setOnClickListener {
            val total = videoPlayer.duration;
            val current = videoPlayer.currentPosition;
            if (current + 5000 <= total){
                videoPlayer.seekTo(current+5000)
            }
            else{
                videoPlayer.seekTo(current)
            }
        }



        btnRePlay.isEnabled = false;
        btnRePlay.setOnClickListener {
            val current = videoPlayer.currentPosition;
            if (current - 5000 >= 0){
                videoPlayer.seekTo(current-5000)
            }
            else{
                videoPlayer.seekTo(current)
            }
        }


        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar?, p1: Int, p2: Boolean) {
            }

            override fun onStartTrackingTouch(seek: SeekBar?) {
            }

            override fun onStopTrackingTouch(seek: SeekBar?) {
                val total = videoPlayer.duration;
                val time = utilities.progressToTimer(seek!!.progress, total)
                videoPlayer.seekTo(time);
                onUpdate();
            }

        })


        if (!videoPlayer.isPlaying){
            val play = Player()
            play.execute();
        }




        relVideo.setOnClickListener{
            isOpen = if (isOpen) {
                hideLayout()
                false
            }
            else{
                showLayout()
                true
            };
        }

//        if (isOpen){
//            hideLayout();
//            isOpen=false;
//        }
//        else{
//            showLayout();
//            isOpen=true;
//        }






        if (Base.dbApp!!.favVideoDao().isFavorite(videoData?.videoId!!) == 1){
            btnFav.setImageResource(R.drawable.baseline_favorite_24)
        }
        else{
            btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
        }

        btnFav.setOnClickListener {
            if (Base.dbApp!!.favVideoDao().isFavorite(videoData?.videoId!!) != 1){
                val favVideo = FavVideo();
                favVideo.videoId = favVideo.videoId;
                favVideo.artistId = favVideo.artistId;
                favVideo.videoName = favVideo.videoName;
                favVideo.videoLinkImage = favVideo.videoLinkImage;
                favVideo.videoLinkVideo = favVideo.videoLinkVideo;
                favVideo.videoTime = favVideo.videoTime;
                Base.dbApp!!.favVideoDao().insertVideo(favVideo);
                btnFav.setImageResource(R.drawable.baseline_favorite_24)
            }
            else{
                val favVideo = FavVideo();
                favVideo.videoId = favVideo.videoId;
                favVideo.artistId = favVideo.artistId;
                favVideo.videoName = favVideo.videoName;
                favVideo.videoLinkImage = favVideo.videoLinkImage;
                favVideo.videoLinkVideo = favVideo.videoLinkVideo;
                favVideo.videoTime = favVideo.videoTime;
                Base.dbApp!!.favVideoDao().deleteVideo(favVideo);
                btnFav.setImageResource(R.drawable.baseline_favorite_border_24)
            }
        }



        btnDownload.setOnClickListener {
            val request = DownloadManager.Request(Uri.parse(videoData!!.videoLinkVideo));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_MOBILE);
            request.setTitle("Download")
            request.setDescription("Download Video ..." + videoData!!.videoName)
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS , "directory/music/"+System.currentTimeMillis()+".mp4");
            val manager = Base.activitySplash.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager;
            manager.enqueue(request)
        }

    }

    private fun hideLayout(){
        relLayoutVideo.visibility =View.GONE;
    }

    private fun showLayout(){
        relLayoutVideo.visibility =View.VISIBLE;
    }



    companion object{
        lateinit var videoPlayer: VideoView;
        lateinit var btnPlay:     ImageView;
        lateinit var btnRePlay:   ImageView;
        lateinit var btnForward:  ImageView;
        lateinit var timeStart:   TextView;
        lateinit var timeEnd:     TextView;
        lateinit var seekbar:     SeekBar;

        lateinit var handler : Handler;
        lateinit var utilities : Utilities;

        class Player(): AsyncTask<Array<Any?> , Any? , Any?>(){

            override fun doInBackground(vararg objects: Array<Any?>): Any? {
                videoPlayer.setOnPreparedListener{
                    videoPlayer.start();
                }
                return null;
            }

            override fun onPostExecute(result: Any?) {
                super.onPostExecute(result)

                videoPlayer.start();
                btnPlay.isEnabled = true;
                btnRePlay.isEnabled = true;
                btnForward.isEnabled = true;
                btnPlay.setImageResource(R.drawable.baseline_pause_24);
                onUpdate()

            }
        }


        private fun onUpdate(){

            var total = videoPlayer.duration.toLong();
            val current = videoPlayer.currentPosition.toLong()

            timeStart.text =utilities.milliSecondsToTimer(current);
            timeEnd.text =utilities.milliSecondsToTimer(total);

            val progress = utilities.getProgressPercentage(current , total);
            seekbar.progress=progress;

            val runnable = Runnable{onUpdate()}
            handler.postDelayed(runnable , 1000)
        }
    }



}
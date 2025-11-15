package com.gogcompany.myapplication.App

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.MainActivity

class DataSource {

    val linkAllArtists = host + "artists.php";
    val linkAllMusics = host + "musics.php";
    val linkAllVideos = host + "videos.php";
    val linkRegister = host + "users.php";
    val linkLogin = host + "login.php";
    val linkCheckLogin = host + "check_login.php";

    fun getAllArtists(requestQueue: RequestQueue){
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET ,
            linkAllArtists ,
            null ,
            { response->
                try {
                    val jsonArray = response.getJSONArray("artist");
                    var i = 0;
                    while (i<jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i);
                        val artistId = jsonObject.getInt("artistId");
                        val artistName = jsonObject.getString("artistName");
                        val artistLinkImage = jsonObject.getString("aristLinkImage");
                        val artistCountMusic = jsonObject.getInt("artistCountMusic");
                        val artistIdSort = jsonObject.getInt("artistIdSort");
                        val artistTypeGender = jsonObject.getInt("artistTypeGender");
                        Log.d("message" , "tester name $artistName, $artistId");

                        val artists = Artists();
                        artists.artistName = artistName;
                        artists.artistLinkImage = artistLinkImage;
                        artists.artistCountMusic = artistCountMusic;
                        artists.artistIdSort = artistIdSort;
                        artists.artistTypeGender = artistTypeGender;


                        val existing = Base.dbApp.artistDao().getArtistByNameAndImage(artistName, artistLinkImage)
                        if (existing == null) {
                            Base.dbApp.artistDao().insertArtist(artists);
                        }

                        i++;
                    }
                }
                catch (e:SQLiteConstraintException){
                    e.printStackTrace();
                }

            } ,
            {error->

            })
        requestQueue.add(jsonObjectRequest);
    }



    fun getAllMusics(requestQueue: RequestQueue){
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET ,
            linkAllMusics ,
            null,
            {response->

                try {
                    val jsonArray = response.getJSONArray("music");
                    var i = 0;
                    while (i<jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i);
                        val musicId = jsonObject.getInt("musicId");
                        val artistId = jsonObject.getInt("artistId");
                        val musicName = jsonObject.getString("musicName");
                        val musicLinkImage = jsonObject.getString("musicLinkImage");
                        val musicLinkMusic = jsonObject.getString("musicLinkMusic");
                        val musicIdSort = jsonObject.getInt("musicIdSort");
                        val musicCategory = jsonObject.getInt("musicCategory");
                        val musicTime = jsonObject.getString("musicTime");

                        val musics = Musics();
                        musics.artistId = artistId;
                        musics.musicName = musicName;
                        musics.musicLinkImage = musicLinkImage;
                        musics.musicLinkMusic = musicLinkMusic;
                        musics.musicIdSort = musicIdSort;
                        musics.musicCategory = musicCategory;
                        musics.musicTime = musicTime;

                        val existing = Base.dbApp.musicDao().getMusicByNameAndImage(musicName, musicLinkMusic)
                        if (existing == null) {
                            Base.dbApp.musicDao().insertMusic(musics);
                        }

                        i++;
                    }
                }
                catch (e:SQLiteConstraintException){
                    e.printStackTrace();
                }

            },
            {error->
                Log.e("TEST_DB", "${error}")
            }
        );
        requestQueue.add(jsonObjectRequest);
    }

    fun getAllVideo(requestQueue: RequestQueue){
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET ,
            linkAllVideos ,
            null ,
            { response->

                val jsonArray = response.getJSONArray("video");
                var i=0;
                while (i< jsonArray.length()){
                    var itemObject = jsonArray.getJSONObject(i);
                    val videoId = itemObject.getInt("videoId");
                    val artistId = itemObject.getInt("artistId");
                    val videoName = itemObject.getString("videoName");
                    val videoLinkImage = itemObject.getString("videoLinkImage");
                    val videoLinkVideo = itemObject.getString("videoLinkVideo");
                    val videoTime = itemObject.getString("videoTime");

                    val video = Video();
                    video.videoId = videoId;
                    video.artistId = artistId;
                    video.videoName = videoName;
                    video.videoLinkImage = videoLinkImage;
                    video.videoLinkVideo = videoLinkVideo;
                    video.videoTime = videoTime;

                    try {
                        val existing = Base.dbApp.VideoDao().getVideoByNameAndImage(videoName, videoLinkVideo)
                        if (existing == null) {
                            Base.dbApp.VideoDao().insertVideo(video);
                        }
                    }
                    catch (e: SQLiteConstraintException){
                        e.printStackTrace();
                    }

                    i++;
                }

            } ,
            { error->

            }
        );
        requestQueue.add(jsonObjectRequest);
    }



    fun registerClient(requestQueue: RequestQueue , activity: Activity ,userName:String, userFamily:String, userEmail:String , userPassword:String , callback:OnFinishRegisterOrLogin){
        var stringRequest = object:  StringRequest(
            Request.Method.POST ,
            linkRegister ,
            {
                response->
                if (response.equals("register user is success")){
                    Toast.makeText(activity , response.toString() , Toast.LENGTH_LONG).show();
                    this.checkLogin(requestQueue, activity , userEmail , userPassword);
                    callback.onFinish();
                }
                else{
                    Toast.makeText(activity , "response Register: " + response.toString() , Toast.LENGTH_LONG).show();
                }
            },
            {
                error->
                Toast.makeText(activity , "response ERR: " + error.message , Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String , String>();
                params.put("user_name" , userName)
                params.put("user_family" , userFamily)
                params.put("user_email" , userEmail)
                params.put("user_password" , userPassword)
                return params;
            }
        }
        requestQueue.add(stringRequest);
    }


    fun loginLogin(requestQueue: RequestQueue, activity: Activity, userEmail:String, userPassword:String, callback:OnFinishRegisterOrLogin){
        var stringRequest = object:  StringRequest(
            Request.Method.POST ,
            linkLogin ,
            {
                response->
                if (response.equals("user is login")){
                    Toast.makeText(activity , response.toString() , Toast.LENGTH_LONG).show();
                    this.checkLogin(requestQueue, activity , userEmail , userPassword);
                    callback.onFinish();
                }
                else{
                    Toast.makeText(activity , "response login: " + response.toString() , Toast.LENGTH_LONG).show();
                }
            },
            {
                error->
                Toast.makeText(activity , "response ERR: " + error.message , Toast.LENGTH_LONG).show();
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String , String>();
                params.put("user_email" , userEmail)
                params.put("user_password" , userPassword)
                return params;
            }
        }
        requestQueue.add(stringRequest);
    }

    fun checkLogin(requestQueue: RequestQueue , activity: Activity  ,userEmail:String, userPassword:String){

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST ,
            linkAllMusics+"?user_email=" + userEmail + "&user_password="+ userPassword,
            null,
            { response->
                try {
                    val jsonArray = response.getJSONArray("isLogin");
                    var i = 0;
                    while (i<jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i);
                        val userId = jsonObject.getInt("id");
                        val userName = jsonObject.getString("name");
                        val userFamily = jsonObject.getString("family");
                        val userEmailIn = jsonObject.getString("email");
                        val userPasswordIn = jsonObject.getString("password");

                        val sp = SharePref(activity);
                        sp.setUserId(userId)
                        sp.setUserName(userName)
                        sp.setUserFamily(userFamily)
                        sp.setUserEmail(userEmailIn)
                        sp.setUserPassword(userPasswordIn)

                        i++;
                    }
                }
                catch (e:SQLiteConstraintException){
                    e.printStackTrace();
                }
            },
            {
                error->
                Toast.makeText(activity , "response ERR: " + error.message , Toast.LENGTH_LONG).show();
            }
        )
        requestQueue.add(jsonObjectRequest);
    }



    interface OnFinishRegisterOrLogin{
        fun onFinish();
    }

}
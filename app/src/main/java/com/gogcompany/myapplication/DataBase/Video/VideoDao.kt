package com.gogcompany.myapplication.DataBase.Video

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.Music.Musics


@Dao
interface VideoDao {

    @Query("select * from videos order by id limit 6")
    fun getLastVideo(): LiveData<List<Video>>;

    @Query("select * from videos")
    fun getAllVideo(): LiveData<List<Video>>;

    @Query("select * from videos where id=:videoId")
    fun getInfoVideo(videoId : Int): Video;

    @Insert
    fun insertVideo(vararg video: Video);


    @Query("SELECT * FROM videos WHERE name_video = :name AND link_video = :link")
    fun getVideoByNameAndImage(name: String, link: String): Video?

    @Query("select * from videos where name_video=:videoName")
    fun searchVideo(videoName:String):LiveData<List<Video>>;

}
package com.gogcompany.myapplication.DataBase.FavVideo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gogcompany.myapplication.DataBase.Video.Video

@Dao
interface FavVideoDao {

    @Query("select * from favVideos")
    fun getAllVideo(): LiveData<List<FavVideo>>

    @Insert
    fun insertVideo(vararg favVideo: FavVideo);

    @Delete
    fun deleteVideo(favVideo: FavVideo);

    @Query("SELECT * FROM videos WHERE name_video = :name AND link_video = :link")
    fun getVideoByNameAndImage(name: String, link: String): Video?

    @Query("select Exists(select 1 from videos where id=:itemId)")
    fun isFavorite(itemId:Int) :Int;

}
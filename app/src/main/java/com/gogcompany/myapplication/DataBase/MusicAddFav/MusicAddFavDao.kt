package com.gogcompany.myapplication.DataBase.MusicAddFav

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gogcompany.myapplication.DataBase.Video.Video


@Dao
interface MusicAddFavDao {

    @Query("select * from musicAddFavs")
    fun getAll():LiveData<List<MusicAddFav>>;

    @Insert
    fun insertMusicAddFav(vararg musicAddFav: MusicAddFav);

    @Delete
    fun deleteMusicAddFav(musicAddFav: MusicAddFav);

    @Query("select exists(select * from musicAddFavs where id=:musicAddFavId)")
    fun isFav(musicAddFavId: Int): Int;

}
package com.gogcompany.myapplication.DataBase.FavMusic

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavMusicDao {

    @Query("select * from favMusics where id = 1")
    fun getAll():LiveData<List<FavMusic>>;

    @Insert
    fun insertFavMusic(vararg music:FavMusic);

    @Delete
    fun deleteFavMusic(music: FavMusic);

    @Query("select Exists(select 1 from favMusics where id=:itemId)")
    fun isFavorite(itemId:Int) :Int;

}
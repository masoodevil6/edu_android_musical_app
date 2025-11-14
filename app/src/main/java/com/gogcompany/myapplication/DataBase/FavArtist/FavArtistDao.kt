package com.gogcompany.myapplication.DataBase.FavArtist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusic

@Dao
interface FavArtistDao {

    @Query("select * from favArtist")
    fun getAll():LiveData<List<FavArtist>>

    @Insert
    fun insertArtistFav(vararg artist:FavArtist);

    @Delete
    fun deleteArtistFav(artist: FavArtist);

    @Query("select exists(select 1 from favMusics where id = :id)")
    fun isFavorite(id:Int) :Int;

}
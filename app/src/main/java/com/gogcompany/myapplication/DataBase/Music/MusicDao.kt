package com.gogcompany.myapplication.DataBase.Music

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gogcompany.myapplication.DataBase.Artist.Artists

@Dao
interface MusicDao {

    @Query("select * from musics")
    fun getAllMusic():LiveData<List<Musics>>

    @Query("select * from musics where id=:musicId")
    fun getInfoMusic(musicId: Int?):Musics

    @Query("select * from musics where item_id=:artistId")
    fun getItemMusicFromArtistId(artistId:Int):LiveData<List<Musics>>

    @Query("select * from musics where category=:category")
    fun getCategoryMusic(category: Int?):LiveData<List<Musics>>

    @Query("SELECT * FROM musics WHERE name_music = :name AND link_music = :link")
    fun getMusicByNameAndImage(name: String, link: String): Musics?

    @Insert
    fun insertMusic(vararg musics: Musics)


    @Query("select * from musics where name_music=:musicName")
    fun searchMusic( musicName:String):LiveData<List<Musics>>;

}
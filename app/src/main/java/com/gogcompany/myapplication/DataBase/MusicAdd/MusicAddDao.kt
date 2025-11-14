package com.gogcompany.myapplication.DataBase.MusicAdd

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.DataBase.Video.Video

@Dao
interface MusicAddDao {

    @Query("select * from musicAdds")
    fun getAll(): LiveData<List<MusicAdd>>;


    @Query("select * from musicAdds where id=:musicId")
    fun getInfoMusic(musicId: Int?): MusicAdd

    @Insert
    fun insertMusic(vararg musicAdd: MusicAdd);

    @Delete
    fun deleteMusic(musicAdd: MusicAdd)


    @Query("select * from musicAdds where music_name=:musicName")
    fun searchMusicAdd(musicName:String):LiveData<List<MusicAdd>>;
}
package com.gogcompany.myapplication.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gogcompany.myapplication.DataBase.Artist.ArtistDao
import com.gogcompany.myapplication.DataBase.Artist.Artists
import com.gogcompany.myapplication.DataBase.FavArtist.FavArtist
import com.gogcompany.myapplication.DataBase.FavArtist.FavArtistDao
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusic
import com.gogcompany.myapplication.DataBase.FavMusic.FavMusicDao
import com.gogcompany.myapplication.DataBase.FavVideo.FavVideo
import com.gogcompany.myapplication.DataBase.FavVideo.FavVideoDao
import com.gogcompany.myapplication.DataBase.Music.MusicDao
import com.gogcompany.myapplication.DataBase.Music.Musics
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAdd
import com.gogcompany.myapplication.DataBase.MusicAdd.MusicAddDao
import com.gogcompany.myapplication.DataBase.MusicAddFav.MusicAddFav
import com.gogcompany.myapplication.DataBase.MusicAddFav.MusicAddFavDao
import com.gogcompany.myapplication.DataBase.Video.Video
import com.gogcompany.myapplication.DataBase.Video.VideoDao

@Database(
    entities = [
        Artists::class ,
        Musics::class ,
        FavMusic::class ,
        FavArtist::class,
        Video::class ,
        FavVideo::class ,
        MusicAdd::class ,
        MusicAddFav::class ,
               ] ,
    version = 1
)
abstract class DBApp:RoomDatabase() {

    abstract fun artistDao():ArtistDao;
    abstract fun musicDao():MusicDao;
    abstract fun favMusicDao():FavMusicDao;
    abstract fun favArtistDao():FavArtistDao;
    abstract fun VideoDao():VideoDao;
    abstract fun favVideoDao():FavVideoDao;
    abstract fun musicAddDao():MusicAddDao;
    abstract fun musicAddFavDao(): MusicAddFavDao;

    var instance:DBApp ?= null;

    companion object {
        @Volatile
        private var instance: DBApp? = null

        fun getInstance(context: Context): DBApp {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    DBApp::class.java,
                    "app_android_musical"
                )
                    .allowMainThreadQueries()
                    .build()
                    .also { instance = it }
            }
        }
    }

}
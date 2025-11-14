package com.gogcompany.myapplication.DataBase.MusicAdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "musicAdds")
class MusicAdd {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int? = null;

    @ColumnInfo(name="music_name")
    var musicName: String? = null;

    @ColumnInfo(name="artist_name")
    var artistName: String? = null;

    @ColumnInfo(name="music_url")
    var musicUrl: String? = null;

}
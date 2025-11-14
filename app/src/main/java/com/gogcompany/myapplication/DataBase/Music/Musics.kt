package com.gogcompany.myapplication.DataBase.Music

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "musics" , indices = [Index(value = ["name_music" , "link_music"] , unique = true)])
class Musics {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var musicId: Int ?=null;

    @ColumnInfo(name = "item_id")
    var artistId: Int ?=null;

    @ColumnInfo(name = "name_music")
    var musicName: String ?=null;

    @ColumnInfo(name = "link_image")
    var musicLinkImage: String ?=null;

    @ColumnInfo(name = "link_music")
    var musicLinkMusic: String ?=null;

    @ColumnInfo(name = "id_sort")
    var musicIdSort: Int ?=null;

    @ColumnInfo(name = "category")
    var musicCategory: Int ?=null;

    @ColumnInfo(name = "time")
    var musicTime: String ?=null;
}
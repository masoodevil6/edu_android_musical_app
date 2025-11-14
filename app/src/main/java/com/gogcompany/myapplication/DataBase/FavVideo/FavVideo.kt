package com.gogcompany.myapplication.DataBase.FavVideo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favVideos" , indices = [Index(value = ["name_video" , "link_video"] , unique = true)])
class FavVideo {

    @PrimaryKey()
    @ColumnInfo(name = "id")
    var videoId: Int ?=null;

    @ColumnInfo(name = "item_id")
    var artistId: Int ?=null;

    @ColumnInfo(name = "name_video")
    var videoName: String ?=null;

    @ColumnInfo(name = "link_image")
    var videoLinkImage: String ?=null;

    @ColumnInfo(name = "link_video")
    var videoLinkVideo: String ?=null;

    @ColumnInfo(name = "time_video")
    var videoTime: String ?=null;

}
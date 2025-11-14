package com.gogcompany.myapplication.DataBase.FavArtist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "favArtist" , indices = [Index(value=["artist_name" , "link_image"], unique=true)])
class FavArtist {

    @PrimaryKey()
    @ColumnInfo(name = "id")
    var artistId:Int?=null;

    @ColumnInfo(name = "artist_name")
    var artistName:String?=null;

    @ColumnInfo(name = "link_image")
    var artistLinkImage:String?=null;


    @ColumnInfo(name = "count_music")
    var artistCountMusic:Int?=null;

    @ColumnInfo(name = "id_sort")
    var artistIdSort:Int?=null;

    @ColumnInfo(name = "type_gender")
    var artistTypeGender:Int?=null;

}
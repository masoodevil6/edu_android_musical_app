package com.gogcompany.myapplication.DataBase.Artist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ArtistDao {

    @Query("select * from artists")
    fun getAll():LiveData<List<Artists>>;

    @Query("select * from artists where id = :artistId")
    fun getInfo(artistId:Int?):Artists;

    @Query("select * from artists where type_gender=:category")
    fun getCategory(category: Int):LiveData<List<Artists>> ;


    @Query("SELECT * FROM artists WHERE artist_name = :name AND link_image = :link")
    fun getArtistByNameAndImage(name: String, link: String): Artists?

    @Insert
    fun insertArtist(vararg artists: Artists);


    @Query("select * from artists where type_gender=:typeGender and artist_name=:artistName")
    fun searchArtist(typeGender: Int , artistName:String):LiveData<List<Artists>>;

}
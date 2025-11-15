package com.gogcompany.myapplication.App

import android.content.Context
import android.content.SharedPreferences

class SharePref(
    private val context: Context
) {

    val shared = context.getSharedPreferences("LOGIN" , 0)
    val editor = shared.edit();

    private val keyIdUser = "keyIdUser";
    private val keyNameUser = "keyNameUser";
    private val keyFamilyUser = "keyFamilyUser";
    private val keyEmailUser = "keyEmailUser";
    private val keyPasswordUser = "keyPasswordUser";

    private val KeyIntro = "KeyIntro";
    private val KeyLogin = "KeyLogin";



    fun getIdName(): Int?{
        return shared.getInt(keyIdUser , 0)
    }
    fun setUserId(userId: Int){
        editor.putInt(keyIdUser , userId);
        editor.commit();
    }

    fun getUserName(): String?{
        return shared.getString(keyNameUser , "nullNameUser")
    }
    fun setUserName(userName: String){
        editor.putString(keyNameUser , userName);
        editor.commit();
    }


    fun getUserFamily(): String?{
        return shared.getString(keyFamilyUser , "nullFamilyUser")
    }
    fun setUserFamily(userFamily: String){
        editor.putString(keyFamilyUser , userFamily);
        editor.commit();
    }


    fun getUserEmail(): String?{
        return shared.getString(keyEmailUser , "nullEmailUser")
    }
    fun setUserEmail(userEmail: String){
        editor.putString(keyEmailUser , userEmail);
        editor.commit();
    }


    fun getUserPassword(): String?{
        return shared.getString(keyPasswordUser , "nullPasswordUser")
    }
    fun setUserPassword(userPassword: String){
        editor.putString(keyPasswordUser , userPassword);
        editor.commit();
    }


    fun getIntro(): Boolean{
        return shared.getBoolean(KeyIntro , false);
    }
    fun setIntro(intro: Boolean){
        editor.putBoolean(KeyIntro , intro);
        editor.commit();
    }




    fun getLogin(): Boolean{
        return shared.getBoolean(KeyLogin , false);
    }
    fun setLogin(login: Boolean){
        editor.putBoolean(KeyLogin , login);
        editor.commit();
    }
}